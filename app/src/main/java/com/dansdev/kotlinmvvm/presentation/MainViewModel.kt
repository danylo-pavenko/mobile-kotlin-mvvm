package com.dansdev.kotlinmvvm.presentation

import androidx.lifecycle.asFlow
import com.dansdev.kotlinmvvm.domain.AnalyticsManager
import com.dansdev.kotlinmvvm.domain.analytic.Event
import com.dansdev.kotlinmvvm.presentation.core.CoreViewModel
import com.dansdev.kotlinmvvm.presentation.core.CoreViewState
import com.dansdev.kotlinmvvm.presentation.core.util.SingleLiveData
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val analyticsManager: AnalyticsManager
): CoreViewModel<MainViewState>() {

    //Add handle networking
    private val networkConnectedLiveData = SingleLiveData<Boolean>()

    fun onConnectedStateUpdate(): Flow<Boolean> = networkConnectedLiveData.asFlow()

    fun sendAnalytics(event: Event) = analyticsManager.sendEvent(event)
}

class MainViewState: CoreViewState()
