package com.melyseev.seasonticket72.di

import android.content.Context
import androidx.room.Room
import com.melyseev.seasonticket72.data.database.DanceDatabase
import com.melyseev.seasonticket72.data.seasonticket.SeasonTicketDao
import com.melyseev.seasonticket72.data.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): DanceDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            DanceDatabase::class.java,
            "dance_database"
        )
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideUserDao(danceDatabase: DanceDatabase): UserDao = danceDatabase.userDao()

    @Provides
    @Singleton
    fun provideSeasonTicketDao(danceDatabase: DanceDatabase): SeasonTicketDao = danceDatabase.seasonTicketDao()
}