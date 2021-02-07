package com.example.todo.todos.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.view.databinding.ListItemTodoBinding

class TodoListAdapter(
    private val itemCallback: TodoItemCallback
) : ListAdapter<Todo, TodoListAdapter.TodoViewHolder>(TodoDiffCallback()) {
    class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean =
            oldItem.uuid == newItem.uuid

    }

    interface TodoItemCallback {
        fun onItemSelected(item: Todo)
        fun onItemCompletionChanged(item: Todo, completion: Boolean)
    }

    class TodoViewHolder(
        private val bindings: ListItemTodoBinding,
        private val itemCallback: TodoItemCallback
    ) : RecyclerView.ViewHolder(bindings.root) {
        companion object {
            fun create(parent: ViewGroup, itemCallback: TodoItemCallback): TodoViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val bindings = ListItemTodoBinding.inflate(inflater, parent, false)
                return TodoViewHolder(bindings, itemCallback)
            }
        }

        fun bind(todo: Todo) {
            bindings.item = todo
            bindings.complete.setOnCheckedChangeListener { _, isChecked ->
                itemCallback.onItemCompletionChanged(todo, isChecked)
            }
            bindings.root.setOnClickListener { itemCallback.onItemSelected(todo) }
            bindings.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder.create(parent, itemCallback)

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
