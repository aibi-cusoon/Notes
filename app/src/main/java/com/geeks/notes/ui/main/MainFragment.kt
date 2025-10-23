package com.geeks.notes.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geeks.notes.App
import com.geeks.notes.R
import com.geeks.notes.data.models.NotesModel
import com.geeks.notes.databinding.FragmentMainBinding
import com.geeks.notes.ui.main.adapter.NotesAdapter
import com.google.android.material.button.MaterialButton

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val noteAdapter = NotesAdapter(::onLongClick, :: onClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupListener()
        getData()
    }

    private fun initView() {
        binding.rvNotes.adapter = noteAdapter
    }

    private fun onLongClick(notesModel: NotesModel) {
        val dialogView = layoutInflater.inflate(R.layout.delete_dialog, null)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()
        val btnDelete = dialogView.findViewById<MaterialButton>(R.id.btn_delete)
        val btnCancel = dialogView.findViewById<MaterialButton>(R.id.btn_cancel)
        btnDelete.setOnClickListener {
            App.db.dao().deleteNote(notesModel)
            getData()
            dialog.dismiss()
        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }

    private fun onClick(notesModel: NotesModel){
        val action = MainFragmentDirections.actionMainFragmentToCreateNotesFragment(notesModel)
        findNavController().navigate(action)

    }

    private fun setupListener() {
        binding.btnCreateNote.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCreateNotesFragment(null)
            findNavController().navigate(action)
        }
    }


    private fun getData() {
        val notesList = App.db.dao().getNotes()
        noteAdapter.addNotes(notesList)
    }
}
