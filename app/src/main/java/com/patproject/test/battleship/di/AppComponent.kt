package com.patproject.test.battleship.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.patproject.test.battleship.MainActivity
import com.patproject.test.battleship.data.SharedPreferencesModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
annotation class AppScope

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent{
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application):Builder
        fun build():AppComponent
    }
}
@Module
class AppModule