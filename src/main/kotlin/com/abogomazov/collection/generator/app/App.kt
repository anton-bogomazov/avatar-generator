package com.abogomazov.collection.generator.app

import com.abogomazov.collection.generator.domain.TraitImportInfo
import com.abogomazov.collection.generator.domain.modules.CollectionGenerator
import com.abogomazov.collection.generator.domain.modules.TokenExporter
import com.abogomazov.collection.generator.domain.modules.TraitSourcesImporter
import com.abogomazov.collection.generator.factories.AdapterFactories
import com.abogomazov.collection.generator.logger


class App private constructor(
    private val importer: TraitSourcesImporter,
    private val generator: CollectionGenerator,
    private val exporter: TokenExporter
) {

    companion object {
        private val logger by logger()

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
        logger.info { "Instantiation successful. Start importing traits." }
        val traits = importer.import()

        logger.info { "${traits.size} traits imported with maximum of " +
                "${traits.maxBy { it.variants.size }.variants.size} variants." }
        val generatedImages = generator.generate(traits)

        logger.info { "${generatedImages.size} images generated, start saving." }
        generatedImages.forEach {
            exporter.write(it)
        }

        logger.info { "Done!" }
    }

}

fun main(args: Array<String>) {
    val config = readConfig(path = args.first()).validated()
    App.instantiate(config).run()
}
