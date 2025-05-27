package org.move.jetbrains.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class MyProjectService(project: Project) {

    init {
        thisLogger().info("Move language service initialized for project: ${project.name}")
    }

    fun getRandomNumber() = (1..100).random()
}