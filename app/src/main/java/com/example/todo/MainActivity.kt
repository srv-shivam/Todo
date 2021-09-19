package com.example.todo

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TodoAdapter.OnItemClickListener {

    private var list = ArrayList<TodoDataCLass>()
    private val adapter = TodoAdapter(list, this)
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

    override fun onItemClick(position: Int) {
        callAlertDialog(position)
    }

    private fun callAlertDialog(position: Int) {
        val alertDialog =
            AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_delete)
                .setTitle("Delete Task")
                .setMessage("Do you really want to delete?")
                .setPositiveButton("Delete") { _: DialogInterface, _: Int ->
                    list.removeAt(position)
                    adapter.notifyItemRemoved(position)
                }
                .setNegativeButton("Cancel") { _: DialogInterface, _: Int -> }
                .create()
                .show()
        return alertDialog
    }

    private fun addTaskToList(task: String) {
        list.add(TodoDataCLass(defaultCheck, task))
        adapter?.notifyItemInserted(list.size - 1)
    }
}