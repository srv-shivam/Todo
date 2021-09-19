package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoAdapter(private var list: List<TodoDataCLass>,
                  private var listener: OnItemClickListener) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = list[position]

        holder.isChecked.isChecked = item.isChecked
        holder.taskTodo.text = item.task
    }

    override fun getItemCount() = list.size

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var isChecked: CheckBox = itemView.cbItemTodo
        var taskTodo: TextView = itemView.tvItemTodo
        private val deleteTask: ImageView = itemView.ivItemTodo

        init {
            deleteTask.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}