package dam.exer_1

fun main() {
    println("Exercise 1")
    println("\na) Using IntArray constructor")
    // Solution nº 1 creates an IntArray with a size of 50, determining that
    // each element is the square of the number after its index.
    val valIntArray = IntArray(50, { i -> (i + 1) * (i + 1) })
    println(
        valIntArray.joinToString(", ")
    )
    // The second solution prints the range of numbers from 1 to 50, mapped to its square.
    println("\nb) Using a range and map()")
    println((1..50).map{it * it})
    // The last solution uses the constructor of the Array object similarly to the IntArray.
    println("\nc) Using Array with constructor")
    val valArray = Array(50, {i -> (i+1) * (i+1)})
    println(valArray.joinToString(", "))

}