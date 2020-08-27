package com.mindorkscodelab.retrofitxcoroutines.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorkscodelab.retrofitxcoroutines.R
import com.mindorkscodelab.retrofitxcoroutines.data.api.ApiHelper
import com.mindorkscodelab.retrofitxcoroutines.data.api.RetrofitBuilder
import com.mindorkscodelab.retrofitxcoroutines.data.model.User
import com.mindorkscodelab.retrofitxcoroutines.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let {
                            users -> submitList(users)
                        }
                    }
                    Status.LOADING -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setupUI() {
        mainAdapter = MainAdapter()
        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            this.adapter = mainAdapter
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun submitList(users : List<User>){
        mainAdapter.apply {
            setData(users)
            notifyDataSetChanged()
        }
    }


}