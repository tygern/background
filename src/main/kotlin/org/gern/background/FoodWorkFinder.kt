package org.gern.background

class FoodWorkFinder : WorkFinder<String> {
    override fun findRequested(): List<String> {
        return listOf("apples", "bread", "peaches", "rice", "avocados")
    }
}