package com.muzo.newsapp.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muzo.newsapp.core.data.model.Article


@Database(
    entities = [Article::class], version = 1, exportSchema = false
)

abstract class NewsDataBase:RoomDatabase() {

    abstract fun getNewsDao():NewsDao
}