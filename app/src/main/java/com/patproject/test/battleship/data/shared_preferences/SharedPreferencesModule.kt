package com.patproject.test.battleship.data.shared_preferences

import android.content.Context
import android.content.SharedPreferences
import com.patproject.test.battleship.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {
    @Provides
    @AppScope
    fun provideSharedPreference(context: Context): SharedPreferences = context.getSharedPreferences(
        SETTINGS_TAG,Context.MODE_PRIVATE)

    companion object{
        const val SETTINGS_TAG = "SETTINGS"
    }
}
