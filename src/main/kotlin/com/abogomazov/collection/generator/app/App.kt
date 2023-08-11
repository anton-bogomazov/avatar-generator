package com.abogomazov.collection.generator.app

import com.abogomazov.collection.generator.domain.TraitImportInfo
import com.abogomazov.collection.generator.factories.AdapterFactories
import com.abogomazov.collection.generator.domain.modules.CollectionGenerator
import com.abogomazov.collection.generator.domain.modules.TokenExporter
import com.abogomazov.collection.generator.domain.modules.TraitSourcesImporter


class App private constructor(
    private val importer: TraitSourcesImporter,
    private val generator: CollectionGenerator,
    private val exporter: TokenExporter
) {

    companion object {
        fun instantiate(config: Config.ValidatedConfig): App {
            val traits = config.traits.map { TraitImportInfo(it.name, it.variants) }
            return App(
                importer = TraitSourcesImporter(traits, AdapterFactories.instantiateReader(config.inputPath)),
                generator = CollectionGenerator,
                exporter = TokenExporter(AdapterFactories.instantiateWriter(config.outputPath))
            )
        }
    }

    fun run() {
        val traits = importer.import()
        val generatedImages = generator.generate(traits)
        generatedImages.forEach {
            exporter.write(it)
        }
    }

}

fun main(args: Array<String>) {
    val config = readConfig(path = args.first()).validated()
    App.instantiate(config).run()
}
