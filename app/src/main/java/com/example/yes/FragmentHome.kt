package com.example.yes

import android.app.Activity
import android.os.Bundle
import android.system.Os.close
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.second_version.adapters.NoteRecyclerAdapter
import com.example.yes.adapters.OnListItemClick
import com.example.yes.adapters.onItemUpdate
import com.example.yes.databinding.FragmentHomeBinding
import com.example.yes.model.entity.NoteDataClass
import com.example.yes.model.local.LocalRepositoryImp
import com.example.yes.model.local.NoteDataBase
import kotlinx.coroutines.*


class FragmentHome : Fragment() , OnListItemClick, onItemUpdate {

    var noteList : List<NoteDataClass> = emptyList()

    var get_NoteTitle:String?=null
    var get_NoteContent:String?=null
    //   var getcontent:String?=null
    var get_NoteDateTime:String?=null


    val noteRecyclerAdapter : NoteRecyclerAdapter by lazy {
        NoteRecyclerAdapter()
    }

    lateinit var binding : FragmentHomeBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var localRepository : LocalRepositoryImp

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noteRecyclerView.setHasFixedSize(true)
        binding.noteRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        toggle = ActionBarDrawerToggle(context as Activity?,binding.drawerlayout,R.string.open,R.string.close)
        binding.drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        setHasOptionsMenu(true)


        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> Toast.makeText(context,"Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.nav_switch_lang -> Toast.makeText(context,"Clicked Switcher", Toast.LENGTH_SHORT).show()
                R.id.nav_group_add -> Toast.makeText(context,"Clicked Groups", Toast.LENGTH_SHORT).show()
                R.id.nav_sync -> Toast.makeText(context,"Clicked Sync Notes", Toast.LENGTH_SHORT).show()
                R.id.nav_delete -> Toast.makeText(context,"Clicked Trash", Toast.LENGTH_SHORT).show()
                R.id.nav_logout -> Toast.makeText(context,"Clicked Logout", Toast.LENGTH_SHORT).show()
                R.id.nav_rate -> Toast.makeText(context,"Clicked Rate Us", Toast.LENGTH_SHORT).show()
                R.id.nav_share -> Toast.makeText(context,"Clicked Share", Toast.LENGTH_SHORT).show()

            }
            true
        }

        binding.imageAddNoteMain.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentHome_to_fragmentCreateNote)
        }





        get_NoteTitle = arguments?.getString("note_title")
        get_NoteContent = arguments?.getString("note_content")
        get_NoteDateTime = arguments?.getString("note_date")


        var db = NoteDataBase.getInstance(requireContext())
        localRepository = LocalRepositoryImp(db)


        binding.noteRecyclerView.adapter = noteRecyclerAdapter

        if(!get_NoteTitle.isNullOrEmpty() && !get_NoteContent.isNullOrEmpty() && !get_NoteDateTime.isNullOrEmpty()){
            GlobalScope.launch (Dispatchers.IO){
                localRepository.insertOrupdate(
                    NoteDataClass(0,get_NoteTitle.toString(),get_NoteContent.toString(),get_NoteDateTime.toString())
                )
            }

        }



        getAllNotes()
        noteRecyclerAdapter.onListItemClick = this
        noteRecyclerAdapter.onitemupdate = this
    }

    fun getAllNotes(){
        GlobalScope.launch(Dispatchers.IO){
            var returnedNotes = async {
                localRepository.getNotes()
            }
            withContext(Dispatchers.Main){
                binding.progressBar.visibility = View.VISIBLE
                noteList = returnedNotes.await()
                binding.progressBar.visibility = View.GONE
                noteRecyclerAdapter.setList(noteList)
            }
        }
    }

    override fun onNoteClick(note: NoteDataClass) {
        GlobalScope.launch ( Dispatchers.IO ){
            localRepository.deleteNote(note)
        }
        getAllNotes()
        Toast.makeText(
            context,
            "The Note Deleted",
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun onNoteCardClick(note: NoteDataClass) {
        Toast.makeText(
            context,
            "The Note Selected",
            Toast.LENGTH_SHORT
        ).show()

        GlobalScope.launch ( Dispatchers.IO ){
            localRepository.deleteNote(note)
        }
        var action = FragmentHomeDirections.actionFragmentHomeToFragmentCreateNote(note.id,note.title,note.note_content,note.dataAndtime)
        findNavController().navigate(action)


    }


}