package com.dansdev.kotlinmvvm.data.state

enum class LoggedState {
    NONE, LOGGED;

    fun isLoggedIn() = this == LOGGED
}
