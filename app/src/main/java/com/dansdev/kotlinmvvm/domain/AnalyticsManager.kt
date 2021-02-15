package com.dansdev.kotlinmvvm.domain

import com.dansdev.kotlinmvvm.domain.analytic.Event
import com.dansdev.kotlinmvvm.domain.analytic.EventLogger

class AnalyticsManager(private val logger: EventLogger) {

    fun sendEvent(event: Event) {
        logger.send(event)
    }
}
