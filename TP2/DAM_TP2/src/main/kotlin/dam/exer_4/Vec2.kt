package dam.exer_4

/**
 * Represents a vector, indicating an X and Y
 */
data class Vec2 (var x : Int, var y : Int) {
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
}