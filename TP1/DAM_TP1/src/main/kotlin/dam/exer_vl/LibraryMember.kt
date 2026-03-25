package org.example.dam.exer_vl

/**
 * Represents a library membrer, storing their name and ID, as well as the books they've borrowed.
 */
data class LibraryMember (
    val name : String,
    val membershipId : Int,
    var borrowedBooks : List<String>) {
}