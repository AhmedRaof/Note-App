package com.example.yes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.yes.databinding.FragmentCreateNoteBinding
import com.example.yes.model.entity.NoteDataClass
import com.example.yes.model.local.LocalRepositoryImp
import com.example.yes.model.local.NoteDataBase
import java.text.SimpleDateFormat
import java.util.*


class FragmentCreateNote : Fragment() {

    lateinit var binding : FragmentCreateNoteBinding
    lateinit var localRepository : LocalRepositoryImp
    lateinit var note : NoteDataClass
    lateinit var currentDateAndTime: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(inflater,container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //For Date and Time
        var simpleDateFormat = SimpleDateFormat("EEEE ,yyyy.MM.dd 'at'  HH:mm:ss z")
        currentDateAndTime = simpleDateFormat.format(Date())
        binding.tvDateTime.text = currentDateAndTime

        var get_id = arguments?.getInt("id")
        var get_title = arguments?.getString("note_title")
        var get_content = arguments?.getString("note_content")
        binding.inputNoteTitle.setText(get_title)
        binding.inputNote.setText(get_content)
        binding.tvDateTime.setText(currentDateAndTime)


        var db = NoteDataBase.getInstance(requireContext())
        localRepository = LocalRepositoryImp(db)

        binding.imageBack.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentCreateNote_to_fragmentHome)
        }

        binding.imagesave.setOnClickListener {
            if(!binding.inputNoteTitle.text.toString().isNullOrEmpty() && !binding.inputNote.text.toString().isNullOrEmpty()  ){
                Toast.makeText(context,"Title is ${binding.tvDateTime.text} ", Toast.LENGTH_SHORT).show()
                var action = FragmentCreateNoteDirections.actionFragmentCreateNoteToFragmentHome(binding.inputNoteTitle.text.toString()
                    ,binding.inputNote.text.toString()
                    ,binding.tvDateTime.text.toString())
                findNavController().navigate(action)
            }
        }
    }



}