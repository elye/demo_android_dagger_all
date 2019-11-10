package com.elyeproj.daggerandroid

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.widget.Toast
import dagger.*
import dagger.android.*
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Inject

data class Data(val message: String)

@Module
object MainActivitySubModule {
    @JvmStatic
    @Provides
    fun data() = Data("My message")
}

class MainActivity : Activity() {
    @Inject lateinit var data: Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, data.message, Toast.LENGTH_SHORT).show()
    }
}

@Subcomponent(modules = [MainActivitySubModule::class])
interface MainActivitySubcomponent: AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<MainActivity>
}

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

@Component(modules = [AndroidInjectionModule::class, MainActivityModule::class])
interface MainApplicationComponent: AndroidInjector<MainApplication>

@Module(subcomponents = [MainActivitySubcomponent::class])
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindYourAndroidInjectorFactory(factory: MainActivitySubcomponent.Factory): AndroidInjector.Factory<*>
}
