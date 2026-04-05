package dam.exer_4

import kotlin.math.sqrt

/**
 * Represents a vector, indicating an X and Y
 */
data class Vec2 (var x : Double, var y : Double) : Comparable<Vec2> {
    /**
     * Adds two vectors.
     */
    operator fun plus (other : Vec2) : Vec2 {
        return Vec2(x + other.x, y + other.y)
    }

    /**
     * Subtracts a vector to another.
     */
    operator fun minus (other : Vec2) : Vec2 {
        return Vec2(x - other.x, y - other.y)
    }

    /**
     * Multiplies two vectors.
     */
    operator fun times (scalar : Double) : Vec2 {
        return Vec2(x * scalar, y * scalar)
    }

    /**
     * Inverts the X and Y of a vector.
     */
    operator fun unaryMinus () : Vec2 {
        return Vec2(-x, -y)
    }

    /**
     * Compares the size of a vector to another, using their magnitudes.
     */
    override fun compareTo(other : Vec2) : Int {
        return magnitude().compareTo(other.magnitude())
    }

    /**
     * Calculates the Euclidean magnitude of a vector.
     */
    fun magnitude() : Double {
        return sqrt(((x * x) + (y * y)))
    }

    /**
     * Dot product of two vectors
     */
    fun dot(other : Vec2) : Double {
        return x * other.x + y * other.y
    }

    /**
     * Normalizes the vector, creating a unit vector in its direction.
     */
    fun normalized() : Vec2 {
        if (magnitude() == 0.0) throw IllegalStateException("Cannot normalize the zero vector.")
        return Vec2(x / magnitude(), y / magnitude())
    }

    /**
     * Obtains a value from the vector
     */
    operator fun get(i : Int) : Double {
        when (i) {
            0 -> return x
            1 -> return y
            else -> throw IndexOutOfBoundsException("The inserted index does not exist in the vector.")
        }
    }

    // The data class already automatically implements component1 and component2.
    // operator fun component1(): Double = x
    // operator fun component2(): Double = y

}

/**
 * Left-hand scalar multiplication of a double with a vector.
 */
operator fun Double.times(vector : Vec2) : Vec2 {
    return vector * this
}

fun main () {
    val a = Vec2 (3.0 , 4.0)
    val b = Vec2 (1.0 , 2.0)
    println ("a = $a") // a = Vec2 (x=3.0 , y =4.0)
    println ("b = $b") // b = Vec2 (x=1.0 , y =2.0)
    println ("a + b = ${a + b}") // a + b = Vec2 (x=4.0 , y =6.0)
    println ("a - b = ${a - b}") // a - b = Vec2 (x=2.0 , y =2.0)
    println ("a * 2.0 = ${a * 2.0} ") // a * 2.0 = Vec2 (x=6.0 , y =8.0)
    println ("-a = ${ -a}") // -a = Vec2 (x= -3.0 , y= -4.0)
    println ("|a| = ${a. magnitude ()}") // |a| = 5.0
    println ("a dot b = ${a.dot(b)}") // a dot b = 11.0
    println (" norm (a) = ${a. normalized ()}") // norm (a) = Vec2 (x=0.6 , y =0.8)
    println ("a[0] = ${a [0]} ") // a[0] = 3.0
    println ("a[1] = ${a [1]} ") // a[1] = 4.0
    println ("a > b = ${a > b}") // a > b = true
    println ("a < b = ${a < b}") // a < b = false
    val vectors = listOf ( Vec2 (1.0 , 0.0) , Vec2 (3.0 , 4.0) , Vec2 (0.0 , 2.0) )
    println (" Longest = ${ vectors.max()}") // Longest = Vec2 (x=3.0 , y =4.0)
    println (" Shortest = ${ vectors.min()}") // Shortest = Vec2 (x=1.0 , y =0.0)

    println("Left-hand scalar mult: 2.0 * a = ${2.0 * a}")
    val (x, y) = a
    println("Destructuring declarations: a = $x,$y")
}