package com.test.showlist

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.showlist.models.MovieData
import com.test.showlist.viewmodels.DataViewModel
import com.test.showlist.adapter.StudentAdapter
import com.test.showlist.models.Content
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import android.R.attr.spacing
import android.content.res.Configuration


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataViewModel: DataViewModel
    val userList: ArrayList<Content> = arrayListOf()

    private var coroutineJob: Job? = null

    lateinit var  adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        dataViewModel= ViewModelProvider(this).get(DataViewModel::class.java)

        dataViewModel.initial(applicationContext)


        val spacing = 30 // 50px
        val includeEdge = true


        adapter = StudentAdapter(applicationContext)

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)


        recyclerView.addItemDecoration(GridSpacingItemDecoration( spacing, includeEdge))
        recyclerView.adapter = adapter


        if (supportActionBar != null)   //null check
                supportActionBar?.setDisplayHomeAsUpEnabled(true)


        if (application.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(GridLayoutManager(this, 3))
        } else {
            recyclerView.setLayoutManager(GridLayoutManager(this, 7))
        }

        getData("")

    }

    fun getData(serText: String){
        coroutineJob?.cancel()
        coroutineJob = lifecycleScope.launch {

            dataViewModel.getFilterData(serText).collect {
                it.let {
                    adapter.submitData(it)
                }
            }

        }
    }


    private var searchView: SearchView? = null

    override fun onCreateOptionsMenu(m: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, m)

        val searchViewMenuItem = m?.findItem(R.id.search)
        searchView = searchViewMenuItem?.getActionView() as SearchView

        searchView?.setQueryHint("Enter Movie Title")



        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.length>3)
                    getData(newText)
                 else
                    getData("")
                return false
            }
        })

        return true
    }
}
