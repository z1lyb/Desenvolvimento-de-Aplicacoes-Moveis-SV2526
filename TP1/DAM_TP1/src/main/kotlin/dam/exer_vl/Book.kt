package org.example.dam.exer_vl

/**
 * Represents a library book, storing its key values.
 */
abstract class Book (
    val title : String,
    val author : String,
    val publicationYear : Int,
    availableCopies : Int
    ) {

    val period : String // period of the book's release
        get() = when {
            publicationYear < 1980 -> "Classic"
            publicationYear < 2010 -> "Modern"
            else -> "Contemporary"
        }

    var availableCopies : Int = availableCopies
        set(value) {
            if (value < 0) {
                println("Sorry, there are no available copies left.")
                return // doesn't update value if there are none
            }
            field = value
            if (value == 0) println("Warning: Book is now out of stock!")
        }

    init {
        println("Book $title by $author has been added to the library.")
    }

    abstract fun getStorageInfo() : String // implemented in child classes
}