package com.patproject.test.battleship

import android.app.Application
import android.content.Context
import com.patproject.test.battleship.di.AppComponent
import com.patproject.test.battleship.di.DaggerAppComponent

class ProjectApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}

val Context.appComponent : AppComponent
    get() =  when(this){
        is ProjectApp -> appComponent
        else -> this.applicationContext.appComponent
}