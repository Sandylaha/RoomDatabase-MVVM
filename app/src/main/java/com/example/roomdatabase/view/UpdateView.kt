package com.example.roomdatabase.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase.R
import com.example.roomdatabase.model.EntityPerson
import com.example.roomdatabase.viewmodel.SharedViewModel

class UpdateView : AppCompatActivity() {

    private lateinit var noteTitleEdt: EditText
    private lateinit var noteEdt: EditText
    private lateinit var saveBtn: Button


    private lateinit var viewModal: SharedViewModel
    private var noteID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_view)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[SharedViewModel::class.java]

        noteTitleEdt = findViewById(R.id.idEdtNoteName)
        noteEdt = findViewById(R.id.idEdtNoteDesc)
        saveBtn = findViewById(R.id.idBtn)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            saveBtn.text = "Update Details"
            noteTitleEdt.setText(noteTitle)
            noteEdt.setText(noteDescription)
        } else {
            saveBtn.text = "Save Details"
        }


        saveBtn.setOnClickListener {

            val noteTitle = noteTitleEdt.text.toString()
            val noteDescription = noteEdt.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val updatedNote = EntityPerson(noteTitle, noteDescription)
                    updatedNote.id = noteID
                    viewModal.updateNote(updatedNote)
                    Toast.makeText(this, "Details Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    viewModal.addNote(EntityPerson(noteTitle, noteDescription))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}