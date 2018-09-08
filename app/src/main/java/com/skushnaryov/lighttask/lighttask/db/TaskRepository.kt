package com.skushnaryov.lighttask.lighttask.db

import com.skushnaryov.lighttask.lighttask.LightTask
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class TaskRepository {
    private val taskDao = LightTask.database.taskDao()

    fun insert(task: Task) = launch(CommonPool) { taskDao.insert(task) }

    fun update(task: Task) = launch(CommonPool) { taskDao.update(task) }

    fun delete(task: Task) = launch(CommonPool) { taskDao.delete(task) }

    fun deleteById(id: Int) = launch(CommonPool) { taskDao.deleteTaskById(id) }

    fun getAllTasks() = taskDao.getAllTasks()

    fun getGroupTasks(group: String) = taskDao.getGroupTasks(group)

    fun getTodayTasks(day: Long) = taskDao.getTodayTasks(day)
}