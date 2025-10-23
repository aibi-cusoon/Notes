package com.geeks.notes.ui.create_notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geeks.notes.App
import com.geeks.notes.data.models.NotesModel
import com.geeks.notes.databinding.FragmentCreateBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreateNotesFragment : Fragment() {
    private lateinit var binding: FragmentCreateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val now = LocalDateTime.now()
        val customFormatter = DateTimeFormatter.ofPattern("dd MMMM HH:mm")
        val formattedDateTimeCustom = now.format(customFormatter)
        binding.tvDate.text = formattedDateTimeCustom

        binding.arrowSave.setOnClickListener {
            val title: String= binding.etTitle.text.toString()
            val desc: String= binding.etDesc.text.toString()
            val date: String = binding.tvDate.text.toString()
            App.db.dao().addNote(NotesModel(title=title, desc = desc, date = date ))
            findNavController().navigateUp()
        }
    }

}