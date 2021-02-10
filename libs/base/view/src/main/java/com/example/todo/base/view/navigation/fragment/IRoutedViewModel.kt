package com.example.todo.base.view.navigation.fragment

import androidx.lifecycle.LiveData
import com.example.todo.base.view.lifecycle.LiveDataEvent
import com.example.todo.base.view.navigation.route.IRoute

interface IRoutedViewModel {
    val route: LiveData<LiveDataEvent<IRoute>>
}
