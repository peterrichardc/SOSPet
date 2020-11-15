package com.labsidea.mypetapp.ui.animals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.labsidea.mypetapp.R
import com.labsidea.mypetapp.data.model.User
import com.labsidea.mypetapp.ui.animals.viewmodel.AnimalsViewModel
import com.labsidea.mypetapp.ui.base.ViewModelFactory
import com.labsidea.mypetapp.ui.main.adapter.MainAdapter
import com.labsidea.mypetapp.utils.RequestStatus
import kotlinx.android.synthetic.main.fragment_animals.*

class AnimalsFragment: Fragment() {

    private lateinit var animalsViewModel: AnimalsViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_animals, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        rvAnimals?.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter()

        rvAnimals?.addItemDecoration(
            DividerItemDecoration(
                rvAnimals?.context,
                (rvAnimals?.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvAnimals?.adapter = adapter
    }

    private fun setupViewModel(){
        animalsViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory()
        ).get(AnimalsViewModel::class.java)
    }

    private fun setupObserver() {
        animalsViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                RequestStatus.SUCCESS -> {
                    progressBar.visibility = View.GONE

                    it.data?.let { users -> renderList(users) }
                    rvAnimals?.visibility = View.VISIBLE
                }
                RequestStatus.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    rvAnimals?.visibility = View.GONE
                }
                RequestStatus.ERROR -> {
                    //TODO -  Handle Error in just one Class.
                    progressBar.visibility = View.GONE

                    Toast.makeText(context, it.messageError, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: ArrayList<User>) = adapter.addData(users).notifyDataSetChanged()

}