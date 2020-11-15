package com.labsidea.mypetapp.ui.main.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.labsidea.mypetapp.data.model.User
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.labsidea.mypetapp.R
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val users: ArrayList<User> = arrayListOf()): RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User?) {
            itemView.textViewUserName.text = user?.name
            itemView.textViewUserEmail.text = user?.email

            if (!user?.logo.isNullOrEmpty())
                Glide.with(itemView.imageViewAvatar.context)
                .load("http://alanfabeni.pythonanywhere.com${user?.logo}")
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(users[position])

    fun addData(list: ArrayList<User>): MainAdapter  {
        users.addAll(list)

        return this
    }
}