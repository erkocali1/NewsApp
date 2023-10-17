package com.muzo.newsapp.core.data.remote.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muzo.newsapp.core.constants.Constants
import com.muzo.newsapp.core.data.model.Article
import com.muzo.newsapp.core.data.remote.api.ResultService
import retrofit2.HttpException

class NewsPagingSource(
    private val resultService: ResultService,
    private val pageSize: Int
) : PagingSource<Int, Article>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val offset = params.key ?: STARTING_PAGE_OFFSET
        val limit = pageSize - 1
        return try {
            val response = resultService.pagination(
                countryCode = "us", pageNumber = limit, apiKey = Constants.API_KEY
            )
            val data = response.articles
            val responseData = mutableListOf<Article>()
            responseData.addAll(data)

            val nextKey = if (responseData.isEmpty()) null else offset + pageSize
            val prevKey = if (offset == STARTING_PAGE_OFFSET) null else offset

            LoadResult.Page(
                data = responseData, nextKey = nextKey, prevKey = prevKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }


    }


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(pageSize)
        }
    }

    companion object {
        private const val STARTING_PAGE_OFFSET = 0
    }

}