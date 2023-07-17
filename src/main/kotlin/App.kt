import api.Cli
import io.TokenExporter


class App(
    private val api: Cli = Cli,
    private val generator: CollectionGenerator = CollectionGenerator,
    private val output: TokenExporter = TokenExporter("output")
) {

    fun run() {
        val traits = Cli.read()
        val images = generator.generate(traits)
        images.forEach {
            output.writeGeneratedImage(it)
        }
    }

}

fun main() {
    App().run()
}
