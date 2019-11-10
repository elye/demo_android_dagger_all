package com.elyeproj.encapsulatedversion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.elyeproj.common.DataInjectFromFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

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
