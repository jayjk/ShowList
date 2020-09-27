package com.test.showlist.models

import com.google.gson.annotations.SerializedName

data class ContentItem (@SerializedName("content")  val content: ArrayList<Content>)