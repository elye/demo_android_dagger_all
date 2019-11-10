package com.elyeproj.original

import android.app.Application
import dagger.Component

class MainApplication: Application() {
    lateinit var component: MainApplicationComponent
    override fun onCreate() {
        super.onCreate()
        component = DaggerMainApplicationComponent.create()
    }
}

@Component
interface MainApplicationComponent {
    val mainActivitySubcomponent: MainActivitySubcomponent
    val mainFragmentSubcomponent: MainFragmentSubcomponent
}
