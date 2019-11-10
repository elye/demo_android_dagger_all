package com.elyeproj.contributeversion

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.elyeproj.common.DataInjectFromActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject lateinit var data: DataInjectFromActivity
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()

        this.findViewById<TextView>(R.id.txt_activity).text = data.message
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}
