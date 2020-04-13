package org.gern.background

class FoodWorkFinder : WorkFinder<String> {
    override fun findRequested(): List<String> {
        println("Found some work")
        return listOf("apples", "bread", "peaches", "rice", "avocados")
    }
}