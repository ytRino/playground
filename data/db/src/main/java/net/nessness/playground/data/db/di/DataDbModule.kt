package net.nessness.playground.data.db.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.nessness.playground.data.db.TimelineDatabase
import net.nessness.playground.data.db.TimelineItemDao
import net.nessness.playground.data.db.TimelineMasterDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDbModule {

    @Singleton
    @Provides
    fun provideReceptionDatabase(@ApplicationContext context: Context): TimelineDatabase {
        return Room.databaseBuilder(context, TimelineDatabase::class.java, "timeline")
            // As now we use db only as cache.
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTimelineMasterDao(db: TimelineDatabase): TimelineMasterDao {
        return db.timelineMasterDao
    }

    @Provides
    fun provideTimelineItemDao(db: TimelineDatabase): TimelineItemDao {
        return db.timelineItemDao
    }
}
