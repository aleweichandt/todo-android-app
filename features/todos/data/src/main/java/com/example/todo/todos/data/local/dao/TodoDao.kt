package com.example.todo.todos.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.todo.base.data.dao.BaseDao
import com.example.todo.todos.data.local.entity.TodoEntity

@Dao
interface TodoDao : BaseDao<TodoEntity> {
    @Query("SELECT * from todo")
    suspend fun getAll(): Array<TodoEntity>

    @Query("SELECT * from todo WHERE :uuid = uuid")
    suspend fun getById(uuid: Long): TodoEntity?

    @Query("DELETE from todo")
    suspend fun deleteAll()
}

@Transaction
suspend fun TodoDao.replaceAll(vararg todos: TodoEntity) {
    deleteAll()
    insert(*todos)
}
