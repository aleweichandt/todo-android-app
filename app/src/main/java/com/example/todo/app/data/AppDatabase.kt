package com.example.todo.app.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.todos.data.local.dao.TodoDao
import com.example.todo.todos.data.local.entity.TodoEntity

private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "todo-database"

@Database(
    entities = [TodoEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    class Builder(private val application: Application) {
        private val builder: RoomDatabase.Builder<AppDatabase>
            get() = Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()

        fun build(): AppDatabase = builder.build()
    }

    abstract fun todoDao(): TodoDao
}
