package com.example.student_pomodoro.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.student_pomodoro.R

class TaskFragment : Fragment() {

    private val taskList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = view.findViewById(R.id.task_list)
        val inputField: EditText = view.findViewById(R.id.input_field)
        val addButton: Button = view.findViewById(R.id.add_button)

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, taskList)
        listView.adapter = adapter

        addButton.setOnClickListener {
            val task = inputField.text.toString().trim()
            if (task.isNotEmpty()) {
                taskList.add(task)
                adapter.notifyDataSetChanged()
                inputField.text.clear()
            } else {
                Toast.makeText(requireContext(), "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        // Long press to delete a task
        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, position, _ ->
            taskList.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }
    }
}