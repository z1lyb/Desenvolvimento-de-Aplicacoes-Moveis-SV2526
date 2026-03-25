package org.example.dam.exer_vl

/**
 * Represents the library, storing its catalogue.
 */
class Library (val name : String){
    var books : MutableList<Book> = mutableListOf()

    fun addBook(book : Book) { // adds a book to the catalogue
        books.add(book)
        totalBooks ++ // increments the value in the companion object
    }

    fun borrowBook(title : String) { // removes a book, if it exists within the list
        val b = books.find { it.title == title }
        if (b != null) {
            var borrowed : Boolean = true
            if(b.availableCopies == 0) borrowed = false
            b.availableCopies -= 1
            if(borrowed) println("Successfully borrowed $title. Copies available: ${b.availableCopies}")
        }
        else println("Book $title was not found in the library system.")
    }

    fun returnBook(title: String) { // re-adds a book to the list, if it's in the system
        val b = books.find { it.title == title }
        if (b != null) {
            b.availableCopies += 1
            println("Book $title returned successfully. Copies available: ${b.availableCopies}")
        }
        else println("Book $title was not found in the library system.")
    }

    fun showBooks() {
        for (book in books) println (book)
    }

    fun searchByAuthor(author: String) { // looks for an author and prints their publications
        val books = books.filter { it.author == author }
        println("Books by $author:")
        for (book in books) println("- ${book.title} (${book.period}, ${book.availableCopies} copies available)")
    }

    companion object { // companion object, keeps track of all the books added to any libraries
        var totalBooks : Int = 0

        fun getTotalBooksCreated() : Int {
            return totalBooks
        }
    }

}