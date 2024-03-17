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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(listOf<AccountData>())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<ListUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch(ioDispatcher) {
            repository.loadData()
            _uiState.value = repository.accounts.value.map {
                it.copy(url = cleanUrl(it.url))
            }
        }
    }

    fun onUiAction(action: ListUiAction) {
        when (action) {
            ListUiAction.CreateAccount -> viewModelScope.launch(ioDispatcher) {
                _uiEvent.send(ListUiEvent.NavigateToAddAccount)
            }
            ListUiAction.ClearCache -> viewModelScope.launch(ioDispatcher) {
                _uiEvent.send(ListUiEvent.ClearImageCache)
            }
            is ListUiAction.EditAccount -> viewModelScope.launch(ioDispatcher) {
                _uiEvent.send(ListUiEvent.NavigateToEditAccount(action.id))
            }
        }
    }

    private fun cleanUrl(url: String): String {
        val cleanedUrl = url.replaceFirst(Regex("^https?://"), "")
        return cleanedUrl.removeSuffix("/")
    }
}
