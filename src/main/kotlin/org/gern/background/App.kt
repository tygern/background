package org.gern.background

import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val workers = (1..3).map { FoodWorker(it.toString())}
    val finder = FoodWorkFinder()

    val scheduler = WorkScheduler(
            finder = finder,
            workers = workers,
            intervalMillis = 5_000
    )

    scheduler.start()
}