package app

import api.CliApi
import app.configuration.Configuration
import app.configuration.parseConfiguration
import generator.CollectionGenerator
import output.FileOutputPlugin
import output.OutputPlugin
import processor.domain.Path

// fixme ?
var HEIGHT: Int = 12
var WIDTH: Int = 12

class App private constructor(
    private val api: CliApi,
    private val generator: CollectionGenerator,
    private val output: OutputPlugin
) {

    companion object Builder {

        // todo handle errors
        fun build(config: Configuration): App {
            val inputPlugin = when(config.plugins.input.name) {
                "cli" -> CliApi()
                else -> { throw error("unexpected") }
            }
            val outputPlugin = when(config.plugins.output.name) {
                "file_export" -> FileOutputPlugin(Path(config.plugins.output.outputPath))
                else -> { throw error("unexpected") }
            }

            return App(inputPlugin, CollectionGenerator, outputPlugin)
        }

    }

    fun run() {
        val traits = api.read()
        val images = generator.generate(traits)
        images.forEach {
            output.writeGeneratedImage(it)
        }
    }

}

fun main() {
    App.build(parseConfiguration()).run()
}
