package com.example.todo.app.di

import com.example.todo.app.navigation.AppRouter
import com.example.todo.app.navigation.DetailsRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    @Singleton
    fun provideAppRouter() = AppRouter()

    @Provides
    @Singleton
    fun provideDetailsRouter() = DetailsRouter()
}
