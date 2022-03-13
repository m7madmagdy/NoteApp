package com.kotlinapps.mvvm

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.kotlinapps.mvvm.ui.viewModel.MainViewModel
import com.kotlinapps.mvvm.ui.viewModel.MainViewModelFactory
import com.kotlinapps.mvvm.ui.NotesAdapter
import com.kotlinapps.mvvm.databinding.ActivityMainBinding
import com.kotlinapps.mvvm.model.Blog

class MainActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainrecycler = findViewById(R.id.recycler)

        requireNotNull(this).application

        val factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        binding.button.setOnClickListener {
            addData()
        }

        initialiseAdapter()

        binding.deleteAll.setOnClickListener {
            if (mainrecycler.layoutManager?.itemCount == 0) {
                Toast.makeText(this, "Nothing to Clear", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.clear()
                Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initialiseAdapter() {
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    private fun observeData() {
        viewModel.mutableLiveData.observe(this) {
            mainrecycler.adapter = NotesAdapter(viewModel, it, this)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData() {
        val titleNote = binding.noteEditText.text.toString()
        if (titleNote.isBlank()) {
            Snackbar.make(binding.root, "Enter Value", Snackbar.LENGTH_LONG).show()
        } else {
            val blog = Blog(titleNote)
            viewModel.add(blog)
            binding.noteEditText.text?.clear()
            mainrecycler.adapter?.notifyDataSetChanged()
        }
    }
}