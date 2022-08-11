package com.example.roomdatabase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.model.EntityPerson
import com.example.roomdatabase.viewmodel.SharedViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ViewAdapter.NoteClickInterface, ViewAdapter.NoteClickDeleteInterface {


    private lateinit var viewModal: SharedViewModel
    private lateinit var notesRV: RecyclerView
    private lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        notesRV = findViewById(R.id.recyclerView)
        addFAB = findViewById(R.id.fab)


        notesRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = ViewAdapter(this, this)


        notesRV.adapter = noteRVAdapter


        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[SharedViewModel::class.java]


        viewModal.allData.observe(this) { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        }
        addFAB.setOnClickListener {

            val intent = Intent(this@MainActivity, UpdateView::class.java)
            startActivity(intent)
            //this.finish()
        }
    }

    override fun onNoteClick(entityPerson: EntityPerson) {
        val intent = Intent(this@MainActivity, UpdateView::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", entityPerson.name)
        intent.putExtra("noteDescription", entityPerson.address)
        intent.putExtra("noteId", entityPerson.id)
        startActivity(intent)
    }

    override fun onDeleteIconClick(entityPerson: EntityPerson) {

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setMessage("Are you sure want to Delete?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->

                viewModal.deleteNote(entityPerson)
                Toast.makeText(this, "${entityPerson.name} Deleted", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}
