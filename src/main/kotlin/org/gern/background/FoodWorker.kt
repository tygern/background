package org.gern.background

import kotlinx.coroutines.delay

class FoodWorker(override val name: String) : Worker<String> {
    override suspend fun execute(task: String) {
        delay(50)
        println("Worker $name working on $task")
    }
}
