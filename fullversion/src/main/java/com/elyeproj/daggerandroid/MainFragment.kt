package com.elyeproj.daggerandroid

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.elyeproj.common.DataInjectFromFragment
import com.elyeproj.common.FragmnetScope
import com.elyeproj.common.MainFragmentSubModule
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Inject

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
        view.findViewById<TextView>(R.id.txt_fragment).text = data.message
    }
}

@FragmnetScope
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
