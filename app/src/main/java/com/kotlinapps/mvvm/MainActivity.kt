package com.kotlinapps.mvvm

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.kotlinapps.mvvm.adapter.MainViewModel
import com.kotlinapps.mvvm.adapter.MainViewModelFactory
import com.kotlinapps.mvvm.adapter.NoteRecyclerAdapter
import com.kotlinapps.mvvm.databinding.ActivityMainBinding
import com.kotlinapps.mvvm.model.Blog

class MainActivity : AppCompatActivity() {

    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        mainrecycler = findViewById(R.id.recycler)
        requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        _binding.button.setOnClickListener {
            addData()
        }
        initialiseAdapter()

        _binding.deleteAll.setOnClickListener {
            viewModel.clear()
        }
    }

    private fun initialiseAdapter() {
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    private fun observeData() {
        viewModel.mutableLiveData.observe(this) {
            mainrecycler.adapter = NoteRecyclerAdapter(viewModel, it, this)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData() {
        val txtplce = findViewById<TextInputEditText>(R.id.titletxt)
        val title = txtplce.text.toString()
        if (title.isBlank()) {
            Snackbar.make(_binding.root, "Enter Value", Snackbar.LENGTH_LONG).show()
        } else {
            val blog = Blog(title)
            viewModel.add(blog)
            txtplce.text?.clear()
            mainrecycler.adapter?.notifyDataSetChanged()
        }
    }
}