package com.elyeproj.original

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.elyeproj.common.ActivityScope
import com.elyeproj.common.DataInjectFromActivity
import com.elyeproj.common.MainActivitySubModule
import dagger.Subcomponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var data: DataInjectFromActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MainApplication).component.mainActivitySubcomponent.inject(this)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()

        this.findViewById<TextView>(R.id.txt_activity).text = data.message
    }
}

@ActivityScope
@Subcomponent(modules = [MainActivitySubModule::class])
interface MainActivitySubcomponent {
    fun inject(mainActivity: MainActivity)
}
