package com.labsidea.mypetapp.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.labsidea.mypetapp.R
import com.labsidea.mypetapp.data.model.User
import com.labsidea.mypetapp.ui.base.ViewModelFactory
import com.labsidea.mypetapp.ui.main.adapter.MainAdapter
import com.labsidea.mypetapp.ui.main.viewmodel.MainViewModel
import com.labsidea.mypetapp.utils.RequestStatus
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter()

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                RequestStatus.SUCCESS -> {
                    progressBar.visibility = View.GONE

                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                RequestStatus.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                RequestStatus.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE

                    Toast.makeText(this, it.messageError, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: ArrayList<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory()
        ).get(MainViewModel::class.java)
    }
}
