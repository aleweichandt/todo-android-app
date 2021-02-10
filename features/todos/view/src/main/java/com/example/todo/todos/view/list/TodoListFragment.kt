package com.example.todo.todos.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todo.base.view.navigation.fragment.StackFragment
import com.example.todo.base.view.navigation.route.IRoute
import com.example.todo.todos.view.R
import com.example.todo.todos.view.databinding.FragmentTodoListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TodoListFragment : StackFragment() {
    @Inject
    override lateinit var router: ITodoListRouter

    override val viewModel by viewModels<TodoListViewModel>()

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
        return bindings.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_add_todo) {
            // TODO navigate to form
            navigate(ITodoListRouter.TodoListRoute.FormRoute)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.onViewCreated()
    }

    override fun navigate(route: IRoute) {
        when (route) {
            is ITodoListRouter.TodoListRoute -> router.push(this, route)
            else -> super.navigate(route)
        }
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
