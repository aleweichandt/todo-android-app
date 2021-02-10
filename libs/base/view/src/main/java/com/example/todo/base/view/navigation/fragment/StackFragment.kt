package com.example.todo.base.view.navigation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.todo.base.view.lifecycle.consume
import com.example.todo.base.view.navigation.exception.IllegalRouteException
import com.example.todo.base.view.navigation.route.IRoute
import com.example.todo.base.view.navigation.router.IRouter

abstract class StackFragment : Fragment() {
    abstract val router: IRouter

    protected abstract val viewModel: IRoutedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRoute()
    }

    private fun observeRoute() {
        viewModel.route.consume(viewLifecycleOwner, this::navigate)
    }

    open fun navigate(route: IRoute) {
        when (route) {
            is IRoute.Back -> router.pop(this)
            else -> throw IllegalRouteException(this, route)
        }
    }
}
