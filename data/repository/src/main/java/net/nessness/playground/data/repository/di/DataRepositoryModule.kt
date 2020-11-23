package net.nessness.playground.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.nessness.playground.data.repository.TimelineRepository
import net.nessness.playground.data.repository.TimelineRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataRepositoryModule {

    @Binds
    @Singleton
    fun bindTimelinRepository(repository: TimelineRepositoryImpl): TimelineRepository
}
