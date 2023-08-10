package com.abogomazov.collection.generator.app

import com.abogomazov.collection.generator.app.io.IO
import com.abogomazov.collection.generator.app.modules.CollectionGenerator
import com.abogomazov.collection.generator.app.modules.TokenExporter
import com.abogomazov.collection.generator.app.modules.TraitSourcesImporter


class App private constructor(
    private val importer: TraitSourcesImporter,
    private val generator: CollectionGenerator,
    private val exporter: TokenExporter
) {

    companion object {
        fun instantiate(config: Config.ValidatedConfig) =
            App(
                importer = TraitSourcesImporter(config.traits, IO(config.inputPath)),
                generator = CollectionGenerator,
                exporter = TokenExporter(IO(config.outputPath))
            )
    }

    fun run() {
        val traits = importer.import()
        val generatedImages = generator.generate(traits)
        generatedImages.forEach {
            exporter.write(it)
        }
    }

}
