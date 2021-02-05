package com.example.todo.todos.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todo.todos.view.databinding.FragmentTodoListBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

/**
 * A simple [Fragment] subclass.
 * Use the [TodoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class TodoListFragment : Fragment() {
    private val viewModel by viewModels<TodoListViewModel>() //TODO init

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bindings = FragmentTodoListBinding.inflate(inflater, container, false)
        bindings.viewModel = viewModel
        with(bindings.todoList) {
            adapter = TodoListAdapter()
        }
        with(bindings.swipeRefresh) {
            setOnRefreshListener(viewModel)
        }
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
    }
}
