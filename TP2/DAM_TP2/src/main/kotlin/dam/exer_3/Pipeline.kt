package dam.exer_3

/**
 * Class that stores, adds to and executes transformations to a list of strings.
 */
class Pipeline {

    // internally stores a list of steps
    private var steps = mutableListOf<((List<String>) -> List<String>)>()

    fun addStage(name : String, transform : (List<String>) -> List<String>) {

    }
}