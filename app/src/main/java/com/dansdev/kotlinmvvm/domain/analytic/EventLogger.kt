package com.dansdev.kotlinmvvm.domain.analytic

interface EventLogger {

    fun send(event: Event)
}
