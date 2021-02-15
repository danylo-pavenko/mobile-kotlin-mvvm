package com.dansdev.kotlinmvvm.data.impl

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.dansdev.kotlinmvvm.data.PrefsRepository
import com.dansdev.kotlinmvvm.data.state.LoggedState

class PrefsRepositoryImpl(context: Context): PrefsRepository {

    companion object {
        private const val NAME = "kotlin_app_prefs"

        private const val KEY_LOGGED_STATE = "key:logged_state"
    }

    private val mainKey = MasterKey.Builder(context.applicationContext)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    private val prefs = EncryptedSharedPreferences.create(
        context.applicationContext,
        NAME,
        mainKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override var loggedState: LoggedState
        get() = LoggedState.valueOf(prefs.getString(KEY_LOGGED_STATE, LoggedState.NONE.name)!!)
        set(value) = prefs.edit { putString(KEY_LOGGED_STATE, value.name) }
}
