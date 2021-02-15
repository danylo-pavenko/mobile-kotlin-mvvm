package com.dansdev.kotlinmvvm.domain.analytic

import android.os.Bundle

sealed class Event(val name: String, val params: Bundle? = null) {
    data class CreateFragment(val screenName: String): Event("OpenScreen: $screenName")
    data class Navigate(val direction: String, val args: Bundle?): Event("Navigate to: $direction", args)
}
