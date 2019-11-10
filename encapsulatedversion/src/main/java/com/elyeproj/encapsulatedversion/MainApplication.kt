package com.elyeproj.encapsulatedversion

import com.elyeproj.common.ActivityScope
import com.elyeproj.common.FragmnetScope
import com.elyeproj.common.MainActivitySubModule
import com.elyeproj.common.MainFragmentSubModule
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.DaggerApplication

class MainApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MainApplication> {
        return DaggerMainApplicationComponent.create()
    }
}

@Component(modules = [AndroidInjectionModule::class, AndroidInjectBuilder::class])
interface MainApplicationComponent: AndroidInjector<MainApplication>

@Module
abstract class AndroidInjectBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules=[MainActivitySubModule::class])
    abstract fun contributeMainActivityAndroidInjector(): MainActivity

    @FragmnetScope
    @ContributesAndroidInjector(modules=[MainFragmentSubModule::class])
    abstract fun contributeMainFragmentAndroidInjector(): MainFragment
}
