package com.example.todo.base.view.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import java.io.Serializable

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class LiveDataEvent<out T>(private val content: T) : Serializable {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun consume(): T? = if (hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

/**
 * Extension that will listen for live data events and consume them right away. This allows us to
 * avoid writing consume on every block.
 */
fun <T : Any> LiveData<LiveDataEvent<T>>.consume(owner: LifecycleOwner, onChanged: (T) -> Unit) =
    this.observe(owner) {
        it.consume()?.let { value ->
            onChanged(value)
        }
    }

