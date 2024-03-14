package com.password.manager.ui.screens.list

import androidx.lifecycle.ViewModel
import com.password.manager.data.Repository
import com.password.manager.data.model.SiteAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: Repository
) : ViewModel() {

    val uiState: StateFlow<List<SiteAccount>> = repository.accounts
}
