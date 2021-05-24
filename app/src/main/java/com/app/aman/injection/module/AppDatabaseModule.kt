package com.app.aman.injection.module

import androidx.room.Room
import com.app.aman.AppApplication
import com.app.aman.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about database
 */
@Module
class AppDatabaseModule(private val application: AppApplication) {
    /**
     * Provides the database object.
     * @return the database object
     */
    @Singleton
    @Provides
    internal fun provideDatabaseObject(): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java, "app-db"
        ).build()
    }
}