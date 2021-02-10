package com.example.todo.base.view.navigation.router

import androidx.fragment.app.Fragment
import java.io.Serializable

interface IArgsRouter<Args: Serializable> {
    fun args(instance: Fragment): Args
}
