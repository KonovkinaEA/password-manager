package com.password.manager.ui.screens.start.model

sealed class StartUiAction {
    data class EnterMasterPassword(val password: String) : StartUiAction()
}
