package com.elyeproj.encapsulatedversion

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.elyeproj.common.DataInjectFromFragment
import com.elyeproj.common.FragmnetScope
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

@Module
object MainFragmentSubModule {
    @JvmStatic
    @Provides
    @FragmnetScope
    fun data() = DataInjectFromFragment("From Fragment")
}

class MainFragment: DaggerFragment() {
    @Inject lateinit var data: DataInjectFromFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.txt_fragment).text = data.message
    }
}
