package com.muzo.newsapp.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muzo.newsapp.core.data.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<Article>)

    @Query("SELECT*FROM articles")
    fun getAllMusic(): Flow<List<Article>>

    @Query("DELETE FROM articles WHERE title= :newsTittle")
    suspend fun deleteNewsById(newsTittle:String)

}