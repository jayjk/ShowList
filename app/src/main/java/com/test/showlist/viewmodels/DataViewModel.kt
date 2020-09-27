package com.test.showlist.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.google.gson.Gson
import com.test.showlist.models.Content
import com.test.showlist.models.MovieData
import com.test.showlist.repository.MoviePagingSource
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONObject


class DataViewModel : ViewModel() {



    var movieData = MutableLiveData<ArrayList<Content>>()

    lateinit var appContext : Context



    fun initial(appContext:Context) {
        this.appContext = appContext;
        changeMainText()
    }

    fun getUsers(): LiveData<ArrayList<Content>> {

        return movieData
    }

    fun changeMainText(){

        val jsonfile: String = appContext.assets.open("data1.json").bufferedReader().use {it.readText()}

        val jsonObject = JSONObject(jsonfile).getJSONObject("page")

        val localData:MovieData = Gson().fromJson(jsonObject.toString(), MovieData::class.java);
        movieData.value = localData.contentItem.content ;
    }

    /*.map {
        it.filter { it.name.contains(queryChannel.value) }
    }*/

    var movies
            : Flow<PagingData<Content>>  = Pager(PagingConfig(pageSize = 3)) {
        MoviePagingSource(appContext)
    }.flow


    lateinit var movie_list: Flow<PagingData<Content>>

    fun getFilterData(serText: String) : Flow<PagingData<Content>>{
        if(serText.isEmpty()){
            movie_list = Pager(PagingConfig(pageSize = 3)) {
                MoviePagingSource(appContext)
            }.flow
            return movie_list;
        }else{
            movie_list = Pager(PagingConfig(pageSize = 3)) {
                MoviePagingSource(appContext)
            }.flow.map { it ->
                it.filter { it.name.toLowerCase().contains(serText) }
            }
            return movie_list;
        }
    }


}

