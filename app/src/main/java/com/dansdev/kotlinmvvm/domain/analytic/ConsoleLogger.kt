package com.dansdev.kotlinmvvm.domain.analytic

import timber.log.Timber

class ConsoleLogger : EventLogger {

    companion object {
        private const val TAG = "Local analytics"
    }

    override fun send(event: Event) {
        Timber.tag(TAG).i("Analytics event: %s", event.name)
    }
}
