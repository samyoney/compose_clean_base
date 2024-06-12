package com.example.compose_clean_base.data.local

import android.content.Context
import androidx.room.Room
import com.example.framework.pref.SharePrefsManager
import com.example.compose_clean_base.BuildConfig
import com.example.compose_clean_base.data.local.dao.StudentDao
import com.example.compose_clean_base.data.local.dao.CourseDao
import com.example.compose_clean_base.data.local.db.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {
    @Provides
    @Singleton
    @Named(value = BuildConfig.DB_NAME)
    fun provideDatabaseName(): String {
        return BuildConfig.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = BuildConfig.DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): RoomDB {
        return Room.databaseBuilder(context, RoomDB::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideSharePrefsManager(
        @ApplicationContext context: Context
    ): SharePrefsManager {
        return SharePrefsManager(context)
    }


    @Provides
    fun provideCourseDao(db: RoomDB): CourseDao {
        return db.courseDao()
    }


    @Provides
    fun provideStudentDao(db: RoomDB): StudentDao {
        return db.studentDao()
    }

}