package com.example.studysmart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [SubjectDao::class, SessionDao::class, TaskDao::class],
    version = 1
)

@TypeConverters
abstract class AppDatabase: RoomDatabase() {
    abstract fun subjectDao(): SubjectDao

    abstract fun taskDao(): TaskDao

    abstract fun sessionDao(): SessionDao
}