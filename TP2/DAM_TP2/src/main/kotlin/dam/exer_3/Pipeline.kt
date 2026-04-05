package dam.exer_3

/**
 * Class that stores, adds to and executes transformations to a list of strings.
 */
class Pipeline {
    // internally stores a list of steps, paired with their names
    private var steps = mutableListOf<( (List<String>) -> List<String> )>()

    /**
     * Adds a named stage to the pipeline.
     * @param name name of the stage
     * @param transform step to add to the pipeline
     */
    fun addStage(name: String, transform: (List<String>) -> List<String>) {
        steps.add(transform);
    }

    /**
     * Creates an output by running the input through every step in the pipeline.
     * @param input String list to put through the pipeline
     * @return output of the pipeline
     */
    fun execute(input: List<String>): List<String> {
        var output = input
        for (step in steps) {
            output = step(output)
        }
        return output
    }

    /**
     * Prints the name of every pipeline step, in order.
     */
    fun describe() {
        for (step in steps) {
            println(step)
        }
    }
}

/**
 * Builds a pipeline by accepting a lambda with the type as its receiver,
 * creating an instance and applying the passed lambda to it.
 * @param lambda function to apply to the pipeline
 * @return configured pipeline
 */
fun buildPipeline(lambda : Pipeline.() -> Unit) : Pipeline {
    val pipeline = Pipeline()
    pipeline.lambda()
    return pipeline
}