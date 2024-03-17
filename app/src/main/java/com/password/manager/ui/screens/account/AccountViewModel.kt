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

    private var accountData: AccountData? = null

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState = _uiState.asStateFlow()

    private val _closeScreen = Channel<Boolean>()
    val closeScreen = _closeScreen.receiveAsFlow()

    init {
        viewModelScope.launch(ioDispatcher) {
            savedStateHandle.get<String>(Account.id)?.let {
                repository.getAccount(it)?.let { account ->
                    _uiState.value = AccountUiState(
                        isNewAccount = false,
                        url = account.url,
                        login = account.login,
                        password = account.password
                    )
                    accountData = account
                }
            }
        }
    }

    fun onUiAction(action: AccountUiAction) {
        when (action) {
            AccountUiAction.CloseAccount -> viewModelScope.launch(ioDispatcher) {
                _closeScreen.send(true)
            }
            AccountUiAction.SaveAccount -> viewModelScope.launch(ioDispatcher) {
                val url = _uiState.value.url
                val login = _uiState.value.login
                val password = _uiState.value.password

                val newAccountData = accountData?.copy(login = login, password = password)
                    ?: AccountData(url = url, login = login, password = password)

                repository.saveAccount(newAccountData)
                _closeScreen.send(true)
            }
            AccountUiAction.DeleteAccount -> viewModelScope.launch(ioDispatcher) {
                accountData?.id?.let { repository.deleteAccount(it) }
                _closeScreen.send(true)
            }
            is AccountUiAction.UpdateWebsite -> viewModelScope.launch(ioDispatcher) {
                _uiState.update { uiState.value.copy(url = action.website) }
            }
            is AccountUiAction.UpdateLogin -> viewModelScope.launch(ioDispatcher) {
                _uiState.update { uiState.value.copy(login = action.login) }
            }
            is AccountUiAction.UpdatePassword -> viewModelScope.launch(ioDispatcher) {
                _uiState.update { uiState.value.copy(password = action.password) }
            }
        }
    }
}

data class AccountUiState(
    val isNewAccount: Boolean = true,
    val url: String = "",
    val login: String = "",
    val password: String = ""
)
