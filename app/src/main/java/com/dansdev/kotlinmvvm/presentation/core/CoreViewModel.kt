package com.dansdev.kotlinmvvm.presentation.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.dansdev.kotlinmvvm.presentation.core.model.AlertMessage
import com.dansdev.kotlinmvvm.presentation.core.state.ProgressState
import com.dansdev.kotlinmvvm.presentation.core.util.SingleLiveData

abstract class CoreViewModel<VS: CoreViewState>: ViewModel() {

    protected val showErrorToast = SingleLiveData<AlertMessage>()
    protected val navigateLiveData = SingleLiveData<NavDirections>()
    protected val progressState = SingleLiveData<ProgressState>()
    protected val viewStateUpdate = MutableLiveData<VS>()

    fun onShowError(): LiveData<AlertMessage> = showErrorToast
    fun onNavigateAction(): LiveData<NavDirections?> = navigateLiveData
    fun onProgressStateUpdate(): LiveData<ProgressState> = progressState

    fun onViewStateUpdate(): LiveData<VS?> = viewStateUpdate

}
