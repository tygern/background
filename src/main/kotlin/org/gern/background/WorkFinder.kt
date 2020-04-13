package org.gern.background

interface WorkFinder<T> {
    fun findRequested(): List<T>
}
