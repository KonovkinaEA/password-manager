package com.password.manager.ui.screens.list

import androidx.lifecycle.ViewModel
import com.password.manager.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: Repository
) : ViewModel() {
}
