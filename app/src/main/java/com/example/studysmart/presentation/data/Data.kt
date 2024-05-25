package com.example.studysmart.presentation.data

import androidx.compose.ui.graphics.toArgb
import com.example.studysmart.domain.model.Session
import com.example.studysmart.domain.model.Subject
import com.example.studysmart.domain.model.Task

object Data {
    val subjects = listOf(
        Subject(name = "Java", goalHours = 10f, colors = Subject.subjectCardColors[0].map { it.toArgb() }, subjectId = 0),
        Subject(name = "C/CPP", goalHours = 10f, colors = Subject.subjectCardColors[1].map { it.toArgb() }, subjectId = 0),
        Subject(name = "Kotlin", goalHours = 10f, colors = Subject.subjectCardColors[2].map { it.toArgb() }, subjectId = 0),
        Subject(name = "Python", goalHours = 10f, colors = Subject.subjectCardColors[3].map { it.toArgb() }, subjectId = 0),
        Subject(name = "C#", goalHours = 10f, colors = Subject.subjectCardColors[4].map { it.toArgb() }, subjectId = 0),
    )
    val tasks = listOf(
        Task(
            title = "Prepare Notes",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = false,
            taskId = 0,
            taskSubjectId = 0
        ),
        Task(
            title = "Do Homework",
            description = "",
            dueDate = 0L,
            priority = 0,
            relatedToSubject = "",
            isComplete = true,
            taskId = 0,
            taskSubjectId = 0
        ),
        Task(
            title = "Go Coaching",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = false,
            taskId = 0,
            taskSubjectId = 0
        ),
        Task(
            title = "Assignment",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = false,
            taskId = 0,
            taskSubjectId = 0
        ),
        Task(
            title = "Write Poem",
            description = "",
            dueDate = 0L,
            priority = 0,
            relatedToSubject = "",
            isComplete = true,
            taskId = 0,
            taskSubjectId = 0
        ),
    )
    val sessions = listOf(
        Session(
            relatedToSubject = "C++",
            date = 0L,
            duration = 2,
            sessionId = 0, sessionSubjectId = 0
        ),
        Session(
            relatedToSubject = "Kotlin",
            date = 0L,
            duration = 2,
            sessionId = 0, sessionSubjectId = 0
        ),
        Session(
            relatedToSubject = "Data",
            date = 0L,
            duration = 2,
            sessionId = 0, sessionSubjectId = 0
        ),
        Session(
            relatedToSubject = "Python",
            date = 0L,
            duration = 2,
            sessionId = 0, sessionSubjectId = 0
        ),
        Session(
            relatedToSubject = "C#",
            date = 0L,
            duration = 2,
            sessionId = 0, sessionSubjectId = 0
        ),
    )
}