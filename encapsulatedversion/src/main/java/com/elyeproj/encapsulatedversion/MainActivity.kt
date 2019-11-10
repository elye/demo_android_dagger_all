package com.elyeproj.encapsulatedversion

import android.os.Bundle
import android.widget.TextView
import com.elyeproj.common.DataInjectFromActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject lateinit var data: DataInjectFromActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()

        this.findViewById<TextView>(R.id.txt_activity).text = data.message
    }
}
