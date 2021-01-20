package com.dicoding.picodiploma.mysubmissionmade.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.local.entity.DetailMovieEntity

@Database(entities = [DetailMovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}