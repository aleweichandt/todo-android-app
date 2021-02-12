package com.example.todo.todos.view.details.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.todo.todos.view.databinding.DialogDeleteTodoBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DeleteTodoDialog : DialogFragment() {
    @Inject
    lateinit var router: IDeleteTodoRouter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bindings = DialogDeleteTodoBinding.inflate(inflater, container, false)
        bindings.lifecycleOwner = viewLifecycleOwner
        bindings.accept.setOnClickListener { onResult(true) }
        bindings.dismiss.setOnClickListener { onResult(false) }
        return bindings.root
    }

    private fun onResult(result: Boolean) {
        router.popWithResult(this, result)
    }
}
