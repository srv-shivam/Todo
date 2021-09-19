package com.example.todo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var list = ArrayList<TodoDataCLass>()
    private val adapter = TodoAdapter(list)
    private val defaultCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            val task = etTaskTodo.text.toString().trim()
            if (task.isEmpty()) {
                Toast.makeText(this, "Enter task todo!", Toast.LENGTH_SHORT).show()
            } else {
                addTaskToList(task)
                etTaskTodo.text = null
            }
        }

        rvTodoList.adapter = adapter
        rvTodoList.layoutManager = LinearLayoutManager(this)
        rvTodoList.setHasFixedSize(true)
    }

    private fun addTaskToList(task: String) {
        list.add(TodoDataCLass(defaultCheck, task))
        adapter?.notifyItemInserted(list.size - 1)
    }
}