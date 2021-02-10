package com.example.todo.base.view.navigation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todo.base.view.lifecycle.LiveDataEvent
import com.example.todo.base.view.navigation.route.IRoute

class RoutedViewModelDelegate<Route : IRoute> : IRoutedViewModel {

    private val _route = MutableLiveData<LiveDataEvent<IRoute>>()
    override val route: LiveData<LiveDataEvent<IRoute>>
        get() = _route

    fun pushRoute(route: Route) {
        _route.value = LiveDataEvent(route)
    }

    fun popRoute() {
        _route.value = LiveDataEvent(IRoute.Back)
    }
}
