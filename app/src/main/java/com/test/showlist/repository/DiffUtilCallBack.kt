package com.test.showlist.repository

import androidx.recyclerview.widget.DiffUtil
import com.test.showlist.models.Content
import com.test.showlist.models.MovieData

class DiffUtilCallBack : DiffUtil.ItemCallback<Content>() {
    override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean = oldItem == newItem
}