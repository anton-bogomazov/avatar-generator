package app

import io.Config
import io.TokenExporter
import io.TraitSourcesImporter
import kotlinx.serialization.json.Json
import java.io.File


class App(
    private val importer: TraitSourcesImporter,
    private val generator: CollectionGenerator = CollectionGenerator,
    private val output: TokenExporter = TokenExporter("output")
) {

    fun run() {
        val traits = importer.import()
        val images = generator.generate(traits)
        images.forEach {
            output.writeGeneratedImage(it)
        }
    }

}
