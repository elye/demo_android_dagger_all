package com.elyeproj.daggerandroid

import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.*
import dagger.android.*
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


data class DataInjectFromActivity(val message: String)

@Module
object MainActivitySubModule {
    @JvmStatic
    @Provides
    fun data() = DataInjectFromActivity("From Activity")
}

class MainActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject lateinit var data: DataInjectFromActivity
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()

        txt_activity.text = data.message
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}

@Subcomponent(modules = [MainActivitySubModule::class])
interface MainActivitySubcomponent: AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<MainActivity>
}

@Module(subcomponents = [MainActivitySubcomponent::class])
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindYourAndroidInjectorFactory(factory: MainActivitySubcomponent.Factory): AndroidInjector.Factory<*>
}
