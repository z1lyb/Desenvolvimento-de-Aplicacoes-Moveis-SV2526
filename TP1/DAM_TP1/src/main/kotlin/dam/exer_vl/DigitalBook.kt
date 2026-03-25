package org.example.dam.exer_vl

/**
 * Subclass of Book, represents a digital release.
 */
class DigitalBook(
    title : String,
    author : String,
    publicationYear : Int,
    availableCopies : Int,
    val fileSize : Double,
    val format : String
    ) : Book (title, author, publicationYear, availableCopies) {

    override fun toString(): String {
        return " Title: $title, Author: $author, Era: $period, Available: $availableCopies \n" + getStorageInfo()
    }

    override fun getStorageInfo(): String {
        return "Storage: Stored digitally: $fileSize MB, Format: $format"
    }
}