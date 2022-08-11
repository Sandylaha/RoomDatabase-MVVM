package com.example.roomdatabase.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.model.EntityPerson

class ViewAdapter(
    private val noteClickInterface: NoteClickInterface,
    private val noteClickDeleteInterface: NoteClickDeleteInterface,
    ):
    RecyclerView.Adapter<ViewAdapter.ViewHolder>() {

    private val allNotes = ArrayList<EntityPerson>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTV: TextView = itemView.findViewById(R.id.txtName)
        val dateTV: TextView = itemView.findViewById(R.id.txtAdd)
        val deleteIV: ImageView = itemView.findViewById(R.id.idIVDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_view,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTV.text = allNotes[position].name
        holder.dateTV.text = allNotes[position].address
        holder.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<EntityPerson>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    interface NoteClickDeleteInterface {
        fun onDeleteIconClick(entityPerson: EntityPerson)
    }

    interface NoteClickInterface {
        fun onNoteClick(entityPerson: EntityPerson)
    }
}