import app.App
import app.CollectionGenerator
import io.Config
import io.TokenExporter
import io.TraitSourcesImporter
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
