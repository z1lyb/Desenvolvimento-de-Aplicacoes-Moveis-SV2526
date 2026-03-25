package org.example.dam.exer_vl

/**
 * Subclass of Book, represents a physical release.
 */
class PhysicalBook (
    title : String,
    author : String,
    publicationYear : Int,
    availableCopies : Int,
    val weight : Int,
    val hasHardcover : Boolean = true
    ) : Book (title, author, publicationYear, availableCopies) {

    override fun toString(): String {
        return "Title: $title, Author: $author, Era: $period, Available: $availableCopies \n" + getStorageInfo()
    }

    override fun getStorageInfo() : String {
        return "Storage: Physical book: ${weight}g, Hardcover: " + if(hasHardcover) "Yes" else "No"
    }
}