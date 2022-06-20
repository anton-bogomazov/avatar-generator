var HEIGHT = 512
var WIDTH = 512
var BASE_PATH = "/"
var OUTPUT_PATH = "/"

//TODO: validate files and print errors, all input
//TODO: create output path if it doesnt exists
// ui state machine
// save graph to file, reorder traits
// scan sources to traits

fun main() {
    askSize()
    askPaths()
    val graph = buildGraph()
    printSummary(graph)
    askAboutGeneration(graph)
}

fun askSize() {
    println("Enter height:")
    HEIGHT = readln().toInt()
    println("Enter width:")
    WIDTH = readln().toInt()
}

fun askPaths() {
    println("Enter path to trait sources:")
    BASE_PATH = readln()
    println("Enter output path:")
    OUTPUT_PATH = readln()
}

fun buildGraph(): TraitsGraph {
    val graph = TraitsGraph()

    while (true) {
        println("Input trait name and number of it (example \"background 4\") or \"done\" of you finished:")
        val input = readln()
        if (input == "done") break
        val trait = input.split(" ")
        graph.addTrait(trait[0], trait[1].toLong())
    }

    return graph
}

fun printSummary(graph: TraitsGraph) = graph.generateVariants(printSummary = true, generateImages = false)

fun askAboutGeneration(graph: TraitsGraph) {
    println("Print OK to generate images:")
    if (readln() == "OK") {
        println("Generating...")
        graph.generateVariants(printSummary = false, generateImages = true)
        println("Done! Enjoy your collection.")
    } else {
        println("OK, bye!")
    }
}
