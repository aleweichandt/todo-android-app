package com.example.todo.todos.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todo.base.view.navigation.fragment.StackFragment
import com.example.todo.base.view.navigation.route.IRoute
import com.example.todo.todos.view.databinding.FragmentTodoDetailsBinding
import com.example.todo.todos.view.details.ITodoDetailsRouter.TodoDetailsRoute

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TodoDetailsFragment : StackFragment() {
    @Inject
    override lateinit var router: ITodoDetailsRouter

    private val args by lazy { router.args(this) }

    override val viewModel by viewModels<TodoDetailsViewModel>()

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
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.onViewCreated(args.uuid)
    }

    override fun navigate(route: IRoute) {
        when (route) {
            is TodoDetailsRoute -> router.pushForResult(this, route, viewModel::onDeleteResult)
            else -> super.navigate(route)
        }
    }

    private fun observeViewModel() {
        viewModel.loadingTodo.observe(viewLifecycleOwner) {
            bindings.swipeRefresh.isRefreshing = it
        }
    }
}
