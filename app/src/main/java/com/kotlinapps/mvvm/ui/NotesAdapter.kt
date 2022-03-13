package com.kotlinapps.mvvm.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapps.mvvm.R
import com.kotlinapps.mvvm.model.Blog
import com.kotlinapps.mvvm.ui.viewModel.MainViewModel

class NotesAdapter(
    private val viewModel: MainViewModel,
    private val blogList: ArrayList<Blog>,
    private val context: Context
) : RecyclerView.Adapter<NotesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NotesViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return NotesViewHolder(viewModel, root)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(blogList[position])
    }

    override fun getItemCount(): Int {
        return blogList.size
    }
}