package com.test.showlist.models

import com.google.gson.annotations.SerializedName

data class MovieData (val title: String,
                      @SerializedName("total-content-items") val itemCount: String,
                      @SerializedName("page-num")  val pageNum: String,
                      @SerializedName("page-size") val pageSize: String,
                      @SerializedName("content-items")  val contentItem: ContentItem)