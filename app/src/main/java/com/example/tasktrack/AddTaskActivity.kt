package com.example.tasktrack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tasktrack.databinding.ActivityAddTaskBinding
import com.example.tasktrack.databinding.ActivityMainBinding

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db : NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = NotesDatabaseHelper(this)

        binding.savetask.setOnClickListener{
            val title = binding.tasktype.text.toString()
            val content = binding.content.text.toString()
            val priority = binding.priority.text.toString()

            val  note = Note(0,title,content,priority)
            db.insertNote(note)
            finish()
            Toast.makeText(this,"Task saved", Toast.LENGTH_SHORT).show()
        }
    }
}