package com.example.mytodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private var taskAdaptor: TaskAdaptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupTaskList()

        binding?.btnAdd?.setOnClickListener {
            val taskName = binding?.etTaskName?.text.toString()

            if (taskName.isEmpty()) {
                Toast.makeText(this, "Invalid name", Toast.LENGTH_LONG).show()
            } else {
                taskAdaptor!!.addTask(Task(taskAdaptor!!.itemCount + 1, taskName))
                binding?.etTaskName?.setText("")
            }
        }

    }

    private fun setupTaskList() {
        taskAdaptor = TaskAdaptor(arrayListOf())
        binding?.rvTaskList?.apply {
            adapter = taskAdaptor
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}