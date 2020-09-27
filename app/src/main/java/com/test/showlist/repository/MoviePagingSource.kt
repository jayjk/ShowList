package com.test.showlist.repository;

import android.content.Context
import androidx.paging.PagingSource
import com.google.gson.Gson
import com.test.showlist.models.Content
import com.test.showlist.models.MovieData
import org.json.JSONObject

class MoviePagingSource(
    val context: Context
) : PagingSource<Int, Content>() {
  
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
    try {
      // Start refresh at page 1 if undefined.
      val nextPage = params.key ?: 1

      val source = "data$nextPage.json"

      val jsonfile: String = context.assets.open(source).bufferedReader().use {it.readText()}

      val jsonObject = JSONObject(jsonfile).getJSONObject("page")

      val localData: MovieData = Gson().fromJson(jsonObject.toString(), MovieData::class.java);


      val response = localData.contentItem.content
        return LoadResult.Page(
        data = response,
        prevKey = if (nextPage == 1) null else nextPage - 1,
        nextKey = nextPage + 1
      )
    } catch (e: Exception) {
        return LoadResult.Error(e)
    }
  }
}