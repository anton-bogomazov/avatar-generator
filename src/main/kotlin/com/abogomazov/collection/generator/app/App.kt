package com.abogomazov.collection.generator.app

import com.abogomazov.collection.generator.io.TokenExporter
import com.abogomazov.collection.generator.io.TraitSourcesImporter


class App(
    private val importer: TraitSourcesImporter,
    private val generator: CollectionGenerator = CollectionGenerator,
    private val output: TokenExporter = TokenExporter("output")
) {

    fun run() {
        val traits = importer.import()
        val images = CollectionGenerator.generate(traits)
        images.forEach {
            output.writeGeneratedImage(it)
        }
    }

}
