package com.password.manager.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.password.manager.data.Repository
import com.password.manager.data.model.AccountData
import com.password.manager.ui.screens.list.model.ListUiAction
import com.password.manager.ui.screens.list.model.ListUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    repository: Repository
) : ViewModel() {

    val uiState: StateFlow<List<AccountData>> = repository.accounts

    private val _uiEvent = Channel<ListUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onUiAction(action: ListUiAction) {
        when (action) {
            ListUiAction.CreateAccount -> {
                viewModelScope.launch(ioDispatcher) {
                    _uiEvent.send(ListUiEvent.NavigateToAddAccount)
                }
            }
            is ListUiAction.EditAccount -> {
                viewModelScope.launch(ioDispatcher) {
                    _uiEvent.send(ListUiEvent.NavigateToEditAccount(action.id))
                }
            }
        }
    }
}
