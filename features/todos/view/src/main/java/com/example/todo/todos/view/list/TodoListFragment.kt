package com.example.todo.todos.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todo.todos.view.R
import com.example.todo.todos.view.databinding.FragmentTodoListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : Fragment() {
    private val viewModel by viewModels<TodoListViewModel>()

    private val adapter by lazy { TodoListAdapter(viewModel) }

    private lateinit var bindings: FragmentTodoListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = FragmentTodoListBinding.inflate(inflater, container, false)
        bindings.viewModel = viewModel
        bindings.lifecycleOwner = viewLifecycleOwner
        bindings.todoList.adapter = adapter
        with(bindings.swipeRefresh) {
            setOnRefreshListener(viewModel)
        }
        bindings.toolbar.setOnMenuItemClickListener { onOptionsItemSelected(it) }
        observeViewModel()
        return bindings.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_add_todo) {
            // TODO navigate to form
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
    }

    private fun observeViewModel() {
        viewModel.todos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.refreshing.observe(viewLifecycleOwner) {
            bindings.swipeRefresh.isRefreshing = it
        }
    }
}
