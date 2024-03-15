package com.password.manager.ui.screens.account

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.password.manager.data.Repository
import com.password.manager.data.model.AccountData
import com.password.manager.ui.navigation.Account
import com.password.manager.ui.screens.account.model.AccountUiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountData())
    val uiState = _uiState.asStateFlow()

    private val _closeScreen = Channel<Boolean>()
    val closeScreen = _closeScreen.receiveAsFlow()

    init {
        viewModelScope.launch(ioDispatcher) {
            savedStateHandle.get<String>(Account.id)?.let {
                repository.getAccountData(it)?.let { account ->
                    _uiState.update { account }
                }
            }
        }
    }

    fun onUiAction(action: AccountUiAction) {
        when (action) {
            AccountUiAction.CloseAccount -> viewModelScope.launch(ioDispatcher) {
                _closeScreen.send(true)
            }
            is AccountUiAction.SaveAccount -> viewModelScope.launch(ioDispatcher) {
//                repository.saveAccountData() TODO
                _closeScreen.send(true)
            }
        }
    }
}
