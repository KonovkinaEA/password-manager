package com.password.manager.ui.screens.account

import androidx.lifecycle.ViewModel
import com.password.manager.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    repository: Repository
) : ViewModel() {
}
