package org.gern.background

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WorkScheduler<T>(
        private val finder: WorkFinder<T>,
        private val workers: List<Worker<T>>,
        private val interval: Long = 10L
) {
    private val channel = Channel<T>(Channel.UNLIMITED)

    suspend fun start() = coroutineScope {
        launch {
            while (true) {
                delay(interval)
                finder.findRequested().forEach { work ->
                    channel.send(work)
                }
            }
        }

        workers.forEach { worker ->
            launch {
                for (work in channel) {
                    worker.execute(work)
                }
            }
        }
    }
}
