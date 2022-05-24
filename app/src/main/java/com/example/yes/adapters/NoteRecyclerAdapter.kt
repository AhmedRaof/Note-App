package com.example.second_version.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.yes.R
import com.example.yes.adapters.OnListItemClick
import com.example.yes.adapters.onItemUpdate
import com.example.yes.model.entity.NoteDataClass

class NoteRecyclerAdapter: RecyclerView.Adapter<NoteRecyclerAdapter.NoteViewHolder>() {

    var onListItemClick : OnListItemClick? = null
    var onitemupdate: onItemUpdate? = null
    var noteList  : List<NoteDataClass> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    fun setList(noteList:List<NoteDataClass>){
        this.noteList = noteList
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lay_textTitle : TextView = itemView.findViewById(R.id.show_note_title)
        var lay_content : TextView = itemView.findViewById(R.id.show_note_content)
        var lay_DateTime : TextView = itemView.findViewById(R.id.show_note_date_time)

        fun bind(note: NoteDataClass){
            lay_textTitle.text = note.title
            lay_content.text = note.note_content
            lay_DateTime.text = note.dataAndtime




            //To delete the note
           itemView.setOnClickListener{
               onListItemClick?.onNoteClick(note)
            }
            //To update the note
            itemView.setOnLongClickListener {

                onitemupdate?.onNoteCardClick(note)
                false
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_container,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var note: NoteDataClass = noteList.get(position)
        holder.bind(note)


    }

    override fun getItemCount(): Int {
        return noteList.size
    }

}