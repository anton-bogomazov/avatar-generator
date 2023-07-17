package com.abogomazov.collection.generator

import com.abogomazov.collection.generator.app.App
import com.abogomazov.collection.generator.app.CollectionGenerator
import com.abogomazov.collection.generator.io.Config
import com.abogomazov.collection.generator.io.TokenExporter
import com.abogomazov.collection.generator.io.TraitSourcesImporter
import kotlinx.serialization.json.Json
import java.io.File

fun readConfig(path: String) = Json.decodeFromString<Config>(File(path).readText())

fun main(args: Array<String>) {
    val config: Config = readConfig(path = args.first())

    App(
        importer = TraitSourcesImporter(config.traits, config.inputPath),
        generator = CollectionGenerator,
        output = TokenExporter(config.outputPath)
    ).run()
}
