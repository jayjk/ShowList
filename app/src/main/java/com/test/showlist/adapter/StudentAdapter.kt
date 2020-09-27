package com.test.showlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.showlist.R
import com.test.showlist.models.Content
import android.graphics.drawable.Drawable
import androidx.paging.PagingDataAdapter
import com.test.showlist.models.MovieData
import com.test.showlist.repository.DiffUtilCallBack
import android.graphics.Movie
import android.widget.Filter
import android.graphics.Typeface




class StudentAdapter(val appcontext: Context) :
    PagingDataAdapter<Content, StudentAdapter.ViewHolder>(DiffUtilCallBack()) {

    //this method is returning the view for each item in the list
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                return ViewHolder(v)
        }

        //this method is binding the data on the StudentAdapter
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            getItem(position)?.let { holder.bindItems(it,appcontext) }
        }


        //the class is hodling the list view
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                fun bindItems(
                    user: Content,
                    appcontext: Context
                ) {
                        val imgTitle = itemView.findViewById(R.id.img_title) as ImageView
                        val txtTitle  = itemView.findViewById(R.id.txt_title) as TextView



                    if (user.poster.contains("poster1"))
                        imgTitle.setImageResource(R.drawable.poster1);
                    else if (user.poster.contains("poster2"))
                        imgTitle.setImageResource(R.drawable.poster2);
                    else if (user.poster.contains("poster3"))
                        imgTitle.setImageResource(R.drawable.poster3);
                    else if (user.poster.contains("poster4"))
                        imgTitle.setImageResource(R.drawable.poster4);
                    else if (user.poster.contains("poster5"))
                        imgTitle.setImageResource(R.drawable.poster5);
                    else if (user.poster.contains("poster6"))
                        imgTitle.setImageResource(R.drawable.poster6);
                    else if (user.poster.contains("poster7"))
                        imgTitle.setImageResource(R.drawable.poster7);
                    else if (user.poster.contains("poster8"))
                        imgTitle.setImageResource(R.drawable.poster8);
                    else if (user.poster.contains("poster9"))
                        imgTitle.setImageResource(R.drawable.poster9);

                    txtTitle.text = user.name

                    val typeface = Typeface.createFromAsset(appcontext.getAssets(), "fonts/titilliumWeb_Light.ttf")
                    txtTitle.setTypeface(typeface)

                }
        }

     /*fun getFilter(): Filter {
        return object : Filter() {
            override  fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    movieListFiltered = movieList
                } else {
                    val filteredList = ArrayList()
                    for (movie in movieList) {
                        if (movie.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(movie)
                        }
                    }
                    movieListFiltered = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = movieListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                movieListFiltered = filterResults.values as ArrayList<Movie>

                notifyDataSetChanged()
            }
        }
    }*/
}