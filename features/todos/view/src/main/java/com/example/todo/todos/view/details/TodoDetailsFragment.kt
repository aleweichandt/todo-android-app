package com.example.todo.todos.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todo.todos.view.databinding.FragmentTodoDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoDetailsFragment : Fragment() {
    private val viewModel by viewModels<TodoDetailsViewModel>()

    private lateinit var bindings: FragmentTodoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindings = FragmentTodoDetailsBinding.inflate(inflater, container, false)
        bindings.vieWModel = viewModel
        bindings.lifecycleOwner = viewLifecycleOwner
        with(bindings.swipeRefresh) {
            setOnRefreshListener(viewModel)
        }
        observeViewModel()
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated(0) //TODO get id as navigation param
    }

    private fun observeViewModel() {
        viewModel.loadingTodo.observe(viewLifecycleOwner) {
            bindings.swipeRefresh.isRefreshing = it
        }
    }
}
