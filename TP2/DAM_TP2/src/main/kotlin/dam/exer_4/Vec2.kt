package dam.exer_4

import kotlin.math.sqrt

/**
 * Represents a vector, indicating an X and Y
 */
data class Vec2 (var x : Double, var y : Double) {
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
    operator fun times (other : Vec2) : Vec2 {
        return Vec2(x * other.x, y * other.y)
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
    operator fun compareTo(other : Vec2) : Int {
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
        if (x == 0.0 || y == 0.0) throw IllegalStateException("Cannot normalize the zero vector.")
        return Vec2(x / x, y / y)
    }

    operator fun get(i : Int) : Double {
        when (i) {
            0 -> return x
            1 -> return y
            else -> throw IndexOutOfBoundsException("The inserted index does not exist in the vector.")
        }
    }
}