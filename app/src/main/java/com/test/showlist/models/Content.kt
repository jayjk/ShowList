package com.test.showlist.models

import com.google.gson.annotations.SerializedName

data class Content (val name: String, @SerializedName("poster-image")  val poster: String)