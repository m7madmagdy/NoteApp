package com.kotlinapps.mvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapps.mvvm.R
import com.kotlinapps.mvvm.model.Blog

class NoteRecyclerAdapter(
    val viewModel: MainViewModel,
    private val blogList: ArrayList<Blog>,
    private val context: Context
) : RecyclerView.Adapter<NoteRecyclerAdapter.NotesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NoteRecyclerAdapter.NotesViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return NotesViewHolder(root)
    }

    override fun onBindViewHolder(holder: NoteRecyclerAdapter.NotesViewHolder, position: Int) {
        holder.bind(blogList[position])
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(blog: Blog) {
            val title: TextView = itemView.findViewById(R.id.title)
            val delete: ImageButton = itemView.findViewById(R.id.delete)
            title.text = blog.title
            delete.setOnClickListener {
                viewModel.remove(blog)
            }
        }
    }
}