package dam.exer_3

/**
 * Class that stores, adds to and executes transformations to a list of strings.
 */
class Pipeline {
    // internally stores a list of steps, paired with their names
    private var steps = mutableListOf<( (List<String>) -> List<String> )>()
    private var stepNames = mutableListOf<String>()

    /**
     * Adds a named stage to the pipeline.
     * @param name name of the stage
     * @param transform step to add to the pipeline
     */
    fun addStage(name: String, transform: (List<String>) -> List<String>) {
        steps.add(transform)
        stepNames += name
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
        for (step in stepNames) println("\t ${stepNames.indexOf(step) + 1}.  $step")
    }

    /**
     * Composes two functions.
     */
    infix fun <T> ((T) -> T).then(f: (T) -> T) : (T) -> T = { t: T -> f(this(t)) }

    /**
     * Merges two stages into one by conjoining their names and transforms.
     * @param stage1 name of first stage
     * @param stage2 name of second stage
     */
    fun compose(stage1 : String, stage2 : String) {
        val index1 = stepNames.indexOf(stage1)
        val index2 = stepNames.indexOf(stage2)

        val newStep = "$stage1 + $stage2" // join names

        val step1 = steps[index1]
        val step2 = steps[index2]

        val joinedSteps = step1.then(step2)

        steps[index1] = joinedSteps
        steps.removeAt(index2)

        stepNames[index1] = newStep
        stepNames.removeAt(index2)
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

/**
 * Runs an input through two pipelines.
 * @param input input for both pipelines
 * @param pipeline1 first pipeline
 * @param pipeline2 second pipeline
 * @return a pair with both results
 */
fun fork (input : List<String>, pipeline1 : Pipeline, pipeline2 : Pipeline) : Pair<List<String>, List<String>> {
    val result1 = pipeline1.execute(input)
    val result2 = pipeline2.execute(input)
    return Pair(result1, result2)
}

fun main() {
    val errorPipeline = buildPipeline({
        addStage("Trim", {list -> list.map {it.trim()} }) // trims each element
        addStage("Filter errors", {list -> list.filter {it.startsWith("ERROR")} }) // only keeps elements that start with ERROR
        addStage("Uppercase", {list -> list.map {it.uppercase()} }) // makes each element uppercase
        addStage("Add index", {list -> list.mapIndexed { idx, str -> "${idx+1}. $str " }})
    })

    val logs = listOf(
        " INFO : server started ",
        " ERROR : disk full ",
        " DEBUG : checking config ",
        " ERROR : out of memory ",
        " INFO : request received ",
        " ERROR : connection timeout "
    )

    val result = errorPipeline.execute(logs)
    println("Pipeline stages:")
    errorPipeline.describe()
    println("Result:")
    for (element in result) {
        println("\t $element")
    }

    errorPipeline.compose("Trim", "Filter errors")
    println("Description of composed pipeline:")
    errorPipeline.describe()
    println("Result (composed pipeline):")
    for (element in errorPipeline.execute(logs)) {
        println("\t $element")
    }

    val infoPipeline = buildPipeline({
        addStage("Trim", {list -> list.map {it.trim()} }) // trims each element
        addStage("Filter errors", {list -> list.filter {it.startsWith("INFO")} }) // only keeps elements that start with ERROR
        addStage("Uppercase", {list -> list.map {it.uppercase()} }) // makes each element uppercase
        addStage("Add index", {list -> list.mapIndexed { idx, str -> "${idx+1}. $str " }})
    })

    val pairResult = fork(logs, errorPipeline, infoPipeline)
    println("Result (error pipeline):")
    for (element in pairResult.first) {
        println("\t $element")
    }
    println("Results (info pipeline):")
    for (element in pairResult.second) {
        println("\t $element")
    }

}