package com.example.mytodo

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.databinding.TaskItemBinding

class TaskAdaptor(private var taskList: ArrayList<Task>) :
    RecyclerView.Adapter<TaskAdaptor.ViewHolder>() {

    inner class ViewHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(task: Task) {
            binding.chkDone.isChecked = task.isDone
            binding.tvTaskName.text = task.name

            binding.chkDone.setOnCheckedChangeListener { _, isChecked ->
                task.isDone = !task.isDone
                toggleStrike(binding.tvTaskName, isChecked)
            }
        }
    }

    private fun toggleStrike(textView: TextView, isChecked: Boolean) {
        if (isChecked) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTask(task: Task) {
        taskList.add(task)
        notifyItemInserted(taskList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.bindData(task)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}