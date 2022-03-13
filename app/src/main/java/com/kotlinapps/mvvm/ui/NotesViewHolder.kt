package com.kotlinapps.mvvm.ui

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapps.mvvm.R
import com.kotlinapps.mvvm.model.Blog
import com.kotlinapps.mvvm.ui.viewModel.MainViewModel

class NotesViewHolder(
    private val viewModel: MainViewModel,
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    fun bind(blog: Blog) {
        val title: TextView = itemView.findViewById(R.id.title)
        val delete: ImageButton = itemView.findViewById(R.id.delete)
        title.text = blog.title
        delete.setOnClickListener {
            viewModel.remove(blog)
        }
    }

}