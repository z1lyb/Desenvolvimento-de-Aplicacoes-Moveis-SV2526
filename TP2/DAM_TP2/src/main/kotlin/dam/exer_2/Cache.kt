package dam.exer_2

class Cache<K : Any, V : Any> {

    val entries = mutableMapOf<K, V>() // entry storage

    /**
     * Inserts or overwrites an entry to the cache
     * @param key The key access
     * @param value Value to associate or overwrite to the key
     */
    fun put(key: K, value: V) {
        entries[key] = value
    }

    /**
     * Gets the value associated with a key
     * @param key Key to search for
     * @return the value associated. Returns null if it doesn't exist.
     */
    fun get(key: K) : V? {
        return entries[key]
    }

    /**
     * Removes the cache entry associated with a key.
     * @param key Key of the entry to be removed
     */
    fun evict(key: K) {
        entries.remove(key)
    }

    /**
     * Size of the cache.
     * @return the total number of entries
     */
    fun size(): Int {
        return entries.size
    }

    /**
     * Gets a key's value, if it exists. Otherwise, calls the lambda passed as a parameter to make and store a value.
     * @param key key to look for a value in
     * @param default function that computes a new value for the key
     * @return the value of the key, if it exists
     */
    fun getOrPut(key: K, default: () -> V) : V {
        var value = entries[key]
        if (value != null) return value  // if the key exists, return the value
        // if the key isn't in the map, puts the value in and returns it
        value = default()
        put(key, value)
        return value
    }

    /**
     * Applies a lambda to a key's value. If the key doesn't exist in the map, this function does nothing.
     * @param key the key to look for
     * @param action action to execute
     * @return whether the action was executed
     */
    fun transform(key: K, action: (V) -> V) : Boolean {
        val value = entries[key]
        if (value != null) {
            entries[key] = action(value)
            return true
        }
        return false
    }

    /**
     * Returns the content of the cache, in an immutable form.
     */
    fun snapshot() : Map<K, V> {
        return entries.toMap() // makes map immutable
    }

    /**
     * Returns an immutable map with the values that satisfy the predicate.
     * @param predicate condition the selected values must satisfy
     * @return an immutable map with the results
     */
    fun filterValues(predicate: (V) -> Boolean) : Map<K, V> {
        return entries.filterValues(predicate).toMap() // filters values by predicate, turns immutable (filterValues returns a mutable map)
    }

}

fun main() {
    // Cache 1 - represents word frequency counts
    val cache1 = Cache<String, Int>()
    // insert values
    cache1.put("kotlin", 1)
    cache1.put("scala", 1)
    cache1.put("haskell", 1)

    println("--- Word frequency cache ---")
    println("Size: ${cache1.size()}")

    println("Frequency of \"kotlin\": ${cache1.get("kotlin")}")

    println("getOrPut \"kotlin\": ${cache1.getOrPut("kotlin", { 1 })}")
    println("getOrPut \"java\": ${cache1.getOrPut("java", { 0 })}")

    println("Transform \"kotlin\" (+1): ${cache1.transform("kotlin", { it + 1 })}")
    println("Transform \"cobol\" (+1): ${cache1.transform("cobol", { it + 1 })}")

    println("Snapshot: ${cache1.snapshot()}")

    // Cache 2 - represents an id-name registry
    val cache2 = Cache<Int, String>()
    // insert values
    cache2.put(1, "Alice")
    cache2.put(2, "Bob")

    println("--- Id registry cache ---")

    println("Id 1 -> ${cache2.get(1)}")
    println("Id 2 -> ${cache2.get(2)}")

    cache2.evict(1)
    println("After evict id 1, size: ${cache2.size()}")

    println("Id 1 after evict id -> ${cache2.get(1)}")

    // filterValues test
    println("filterValues test: ${cache1.filterValues { it < 1 }}")
}