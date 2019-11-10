package com.elyeproj.contributeversion


import android.app.Application
import com.elyeproj.common.ActivityScope
import com.elyeproj.common.FragmnetScope
import com.elyeproj.common.MainActivitySubModule
import com.elyeproj.common.MainFragmentSubModule
import dagger.Component
import dagger.Module
import dagger.android.*
import javax.inject.Inject

class MainApplication: Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerMainApplicationComponent.create()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
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
