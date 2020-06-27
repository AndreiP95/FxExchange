package com.example.mainactivity.dagger.modules

import com.example.mainactivity.dagger.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: MyApplication) {

    @Provides
    @Singleton
    fun providesApplication(): MyApplication {
        return application
    }
}