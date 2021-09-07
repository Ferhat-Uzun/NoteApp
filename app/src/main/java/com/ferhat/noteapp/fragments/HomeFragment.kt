package com.ferhat.noteapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ferhat.noteapp.MainActivity
import com.ferhat.noteapp.R
import com.ferhat.noteapp.adapter.NoteAdapter
import com.ferhat.noteapp.databinding.FragmentHomeBinding
import com.ferhat.noteapp.model.Note
import com.ferhat.noteapp.viewModel.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter : NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel
        setUpRecyclerView()

        binding.fabAddNote.setOnClickListener { mView->
            mView.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }
    }

    private fun setUpRecyclerView(){
        noteAdapter = NoteAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )

            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity.let {
            noteViewModel.getAllNotes().observe(viewLifecycleOwner,{ note->
                noteAdapter.differ.submitList(note)
                UpdateUI(note)
            })
        }
    }

    private fun UpdateUI(note : List<Note>){

        if (note.isNotEmpty()){
            binding.recyclerView.visibility = View.VISIBLE
            binding.tvNoNoteAvailable.visibility = View.GONE
        }else{
            binding.recyclerView.visibility = View.GONE
            binding.tvNoNoteAvailable.visibility = View.VISIBLE
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}