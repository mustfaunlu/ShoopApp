package com.mustafaunlu.shoopapp.di.sharedpref // ktlint-disable filename

import android.content.Context
import android.content.SharedPreferences
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_FILE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
    }
}
