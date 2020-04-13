package org.gern.background

interface Worker<T> {
    val name: String
    suspend fun execute(task: T)
}
