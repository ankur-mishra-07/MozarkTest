package com.test.mozarktest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.mozarktest.R
import com.test.mozarktest.model.UserResult

class UserDataRecyclerViewAdapter(listener: onUserClicked) :
    RecyclerView.Adapter<UserDataRecyclerViewAdapter.ViewHolder>() {

    private var mUserList: List<UserResult> = arrayListOf()
    private lateinit var mContext: Context
    private var listener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mUserList[position]
        holder.idView.text = item.name.title + " " + item.name.first
        Glide.with(mContext)
            .load(item.picture.large)
            .into(holder.itemImage)
        holder.itemView.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int = mUserList.size
    fun setRefreshData(
        search: List<UserResult>,
        requireContext: Context
    ) {
        this.mUserList = search
        this.mContext = requireContext
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_poster_title)
        val itemImage: AppCompatImageView = view.findViewById(R.id.item_poster_post)

        override fun toString(): String {
            return super.toString() + " '" + idView.text + "'"
        }
    }

    interface onUserClicked {
        fun onClicked(mUser: UserResult)
    }
}