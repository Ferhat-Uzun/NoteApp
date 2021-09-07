package com.ferhat.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ferhat.noteapp.databinding.ActivityMainBinding
import com.ferhat.noteapp.db.NoteDatabase
import com.ferhat.noteapp.repository.NoteRepository
import com.ferhat.noteapp.viewModel.NoteViewModel
import com.ferhat.noteapp.viewModel.NoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setUpViewModel()
    }


    private fun setUpViewModel(){

        val noteRepository = NoteRepository(
            NoteDatabase(this)
        )

        val viewModelProviderFactory = NoteViewModelProviderFactory(application,noteRepository)

        noteViewModel = ViewModelProvider(this,viewModelProviderFactory).get(NoteViewModel::class.java)

    }


}