package com.password.manager.ui.screens.start.model

sealed class StartUiAction {
    data class UpdateMasterPassword(val text: String) : StartUiAction()
    data class EnterMasterPassword(val password: String) : StartUiAction()
}
