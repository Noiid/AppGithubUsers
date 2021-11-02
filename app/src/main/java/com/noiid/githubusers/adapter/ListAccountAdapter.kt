package com.noiid.githubusers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noiid.githubusers.R
import com.noiid.githubusers.dataclass.Users
import java.util.*

class ListAccountAdapter(private val listAccount: ArrayList<Users>) : RecyclerView.Adapter<ListAccountAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (username, photo) = listAccount[position]
        holder.tvUsername.text = holder.itemView.context.getString(R.string.text_username, username)
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.transition)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAccount[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listAccount.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvUsername: TextView = itemView.findViewById(R.id.tv_item_username)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Users)
    }


}