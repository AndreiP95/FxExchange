package com.example.mainactivity.dagger.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.mainactivity.dagger.MyApplication
import com.example.mainactivity.utils.BASE_URI
import com.example.mainactivity.utils.USER_PREFS
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RepoModule {

    /**
     * Get shared preferences with user saved data
     *
     * @param application MyApplication
     * @return SharedPreferences
     */

    @Provides
    @Singleton
    fun providesSharedPreferences(application: MyApplication): SharedPreferences {
        return application.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)
    }

    /**
     * Method for providing Json parser & builder
     * @return Gson
     */

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    /**
     * Method for providing client for Retrofit usage
     * @return OkHttpClient
     */

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .build()
    }

    /**
     * Provides Retrofit instance for networking calls
     *
     * @param gson Gson
     * @param okHttpClient OkHttpClient
     * @return Retrofit
     */

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URI)
            .client(okHttpClient)
            .build()
    }

}