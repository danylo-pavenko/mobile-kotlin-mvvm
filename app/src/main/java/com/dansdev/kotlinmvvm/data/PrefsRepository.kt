package com.dansdev.kotlinmvvm.data

import com.dansdev.kotlinmvvm.data.state.LoggedState

interface PrefsRepository {

    var loggedState: LoggedState
}
