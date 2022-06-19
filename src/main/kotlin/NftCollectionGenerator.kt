const val HEIGHT = 512
const val WIDTH = 512
const val BASE_PATH = "/Users/antonbogomazov/Desktop/idols_src"

fun main() {
    val graph = TraitsGraph()
        .addTrait("background", 4)
        .addTrait("face", 2)
        .addTrait("eyes", 2)
        .addTrait("nose", 3)
        .addTrait("mouth", 3)
        .addTrait("ear", 3)
        .addTrait("haircut", 3)

    graph.generateVariants(generateImages = true)
}
