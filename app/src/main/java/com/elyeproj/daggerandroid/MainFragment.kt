package com.elyeproj.daggerandroid

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


data class DataInjectFromFragment(val message: String)

@Module
object MainFragmentSubModule {
    @JvmStatic
    @Provides
    fun data() = DataInjectFromFragment("From Fragment")
}

class MainFragment: Fragment() {
    @Inject lateinit var data: DataInjectFromFragment


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_fragment.text = data.message
    }
}

@Subcomponent(modules = [MainFragmentSubModule::class])
interface MainFragmentSubcomponent: AndroidInjector<MainFragment> {
    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<MainFragment>
}

@Module(subcomponents = [MainFragmentSubcomponent::class])
abstract class MainFragmentyModule {
    @Binds
    @IntoMap
    @ClassKey(MainFragment::class)
    abstract fun bindYourAndroidInjectorFactory(factory: MainFragmentSubcomponent.Factory): AndroidInjector.Factory<*>
}
