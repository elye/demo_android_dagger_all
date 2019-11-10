package com.elyeproj.common

import dagger.Module
import dagger.Provides

@Module
object MainActivitySubModule {
    @JvmStatic
    @Provides
    @ActivityScope
    fun data() = DataInjectFromActivity("From Activity")
}

@Module
object MainFragmentSubModule {
    @JvmStatic
    @Provides
    @FragmnetScope
    fun data() = DataInjectFromFragment("From Fragment")
}
