package com.geeks.notes.ui.create_notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.geeks.notes.App
import com.geeks.notes.data.models.NotesModel
import com.geeks.notes.databinding.FragmentCreateBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreateNotesFragment : Fragment() {
    private lateinit var binding: FragmentCreateBinding
    private val args: CreateNotesFragmentArgs by navArgs()

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
        val notesModel = args.notes

        notesModel?.let {it ->
            binding.etTitle.setText(it.title)
            binding.etDesc.setText(it.desc)
        }


        binding.arrowSave.setOnClickListener {
            val title: String = binding.etTitle.text.toString()
            val desc: String = binding.etDesc.text.toString()
            val date: String = binding.tvDate.text.toString()


            if (notesModel == null) {
                App.db.dao().addNote(
                    NotesModel(
                        title = title,
                        desc = desc,
                        date = date
                    )
                )
            } else {
                App.db.dao().addNote(
                    NotesModel(
                        id = notesModel.id,
                        title = title,
                        desc = desc,
                        date = date
                    )
                )
            }

            findNavController().navigateUp()
        }
    }

}