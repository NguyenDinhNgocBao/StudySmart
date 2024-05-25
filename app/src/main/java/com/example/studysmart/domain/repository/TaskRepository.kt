package com.example.studysmart.domain.repository

import com.example.studysmart.domain.model.Subject
import com.example.studysmart.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun upsertTask(task: Task)

    suspend fun deleteTask(taskId: Int)

    suspend fun getTaskById(taskId: Int): Task?

    fun getUpcomingTaskForSubject(subjectInt: Int): Flow<List<Task>>

    fun getCompletedTaskForSubject(subjectInt: Int): Flow<List<Task>>

    fun getAllUpcomingTasks(): Flow<List<Task>>
}