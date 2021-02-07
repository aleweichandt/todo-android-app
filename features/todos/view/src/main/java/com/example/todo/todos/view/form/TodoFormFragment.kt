package com.example.todo.todos.view.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todo.todos.view.databinding.FragmentTodoFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoFormFragment : Fragment() {
    private val viewModel by viewModels<TodoFormViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bindings = FragmentTodoFormBinding.inflate(inflater, container, false)
        bindings.vieWModel = viewModel
        bindings.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
        return bindings.root
    }

    private fun observeViewModel() {

    }

}
