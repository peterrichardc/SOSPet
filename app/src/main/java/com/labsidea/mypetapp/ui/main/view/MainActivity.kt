package com.labsidea.mypetapp.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.labsidea.mypetapp.R
import com.labsidea.mypetapp.data.model.User
import com.labsidea.mypetapp.ui.main.adapter.MainAdapter
import com.labsidea.mypetapp.ui.main.viewmodel.MainViewModel
import com.labsidea.mypetapp.utils.RequestStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menuHome -> {
                //TODO
                return@OnNavigationItemSelectedListener true
            }
            R.id.menuEvents -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.menuProfile -> {
                //TODO
                return@OnNavigationItemSelectedListener true
            }
            R.id.menuOthers -> {

                //TODO
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        setupUI()

        setupObserver()
    }

    private fun setupUI() {
        adapter = MainAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)

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
                    //TODO -  Handle Error in just one Class.
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
}

