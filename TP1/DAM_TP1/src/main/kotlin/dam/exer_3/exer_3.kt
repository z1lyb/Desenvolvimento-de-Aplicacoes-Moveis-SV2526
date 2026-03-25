package org.example.dam.exer_3

fun main(){
    println("Exercise 3 - Ball drop")
    // Generates a sequence that begins at 100 meters, defines it as the following jumps being 60% of the previous.
    val sequence = generateSequence(Pair(0, 100.0),
        {Pair (it.first + 1, it.second * 0.6)})
        .takeWhile { it.second > 1.0 } // takes values while the jumps are larger than 1m
    val initialBounces= sequence.take(15).toList()
    val bounceHeights = initialBounces.map {(String.format("%.2f", it.second))} // maps the values to the same rounded to 2 decimals
    println("Bounce heights: " + bounceHeights.joinToString("  "))
}