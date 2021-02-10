package com.example.todo.todos.view.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todo.base.view.navigation.fragment.StackFragment
import com.example.todo.todos.view.databinding.FragmentTodoFormBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TodoFormFragment : StackFragment() {
    @Inject
    override lateinit var router: ITodoFormRouter

    override val viewModel by viewModels<TodoFormViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bindings = FragmentTodoFormBinding.inflate(inflater, container, false)
        bindings.vieWModel = viewModel
        bindings.lifecycleOwner = viewLifecycleOwner
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
    }

}
