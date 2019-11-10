package com.elyeproj.encapsulatedversion

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.elyeproj.common.ActivityScope
import com.elyeproj.common.DataInjectFromActivity
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


@Module
object MainActivitySubModule {
    @JvmStatic
    @Provides
    @ActivityScope
    fun data() = DataInjectFromActivity("From Activity")
}

class MainActivity : DaggerAppCompatActivity() {
    @Inject lateinit var data: DataInjectFromActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()

        this.findViewById<TextView>(R.id.txt_activity).text = data.message
    }
}
