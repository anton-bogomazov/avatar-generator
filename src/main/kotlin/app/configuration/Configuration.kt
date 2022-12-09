package app.configuration

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

//todo class hierrchy for plugins reflects config
@Serializable
data class InputPlugin(
    val name: String
)

@Serializable
data class OutputPlugin(
    val name: String,
    val outputPath: String
)

@Serializable
data class Plugins(
    val input: InputPlugin,
    val output: OutputPlugin
)

@Serializable
data class Configuration(
    val plugins: Plugins
)

// fixme too low level, use another api
fun parseConfiguration(): Configuration {
    val configString = InputStreamReader(
        Thread.currentThread().contextClassLoader.getResourceAsStream("configuration.yaml"), StandardCharsets.UTF_8).readText()
    return Yaml.default.decodeFromString(Configuration.serializer(), configString)
}
