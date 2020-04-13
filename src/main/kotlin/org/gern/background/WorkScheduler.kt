package org.gern.background

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WorkScheduler<T>(
        private val finder: WorkFinder<T>,
        private val workers: List<Worker<T>>,
        private val intervalMillis: Long = 10L
) {
    private val channel = Channel<T>(Channel.UNLIMITED)

    suspend fun start() = coroutineScope {
        every(intervalMillis) {
            findWork()
        }

        workers.forEach {
            listenForWork(it)
        }
    }

    private suspend fun findWork() =
            finder.findRequested().forEach { work ->
                channel.send(work)
            }

    private fun CoroutineScope.listenForWork(worker: Worker<T>) =
            launch {
                for (work in channel) {
                    worker.execute(work)
                }
            }

    private fun CoroutineScope.every(intervalMillis: Long, block: suspend () -> Unit) =
            launch {
                while (true) {
                    delay(intervalMillis)
                    block()
                }
            }
}
