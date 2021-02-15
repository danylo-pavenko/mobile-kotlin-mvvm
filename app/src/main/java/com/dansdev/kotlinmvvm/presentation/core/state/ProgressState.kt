package com.dansdev.kotlinmvvm.presentation.core.state

sealed class ProgressState {
    object Show: ProgressState()
    object Hide: ProgressState()
}
