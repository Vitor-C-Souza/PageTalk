package com.pagetalk.app.ui.presentation.importpdf

sealed class UploadStatus {
    object Complete : UploadStatus()
    data class InProgress(val progress: Float) : UploadStatus()
    object Queued : UploadStatus()
}
