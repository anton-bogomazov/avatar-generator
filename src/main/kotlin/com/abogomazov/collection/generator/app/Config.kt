package com.abogomazov.collection.generator.app

import com.abogomazov.collection.generator.domain.TraitName
import com.abogomazov.collection.generator.domain.VariantName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Path
import kotlin.io.path.notExists


fun readConfig(path: String) = Json.decodeFromString<Config>(File(path).readText())

@Serializable
data class Config(
    val inputPath: String,
    val outputPath: String,
    val traits: List<TraitConfig>,
) {
    fun validated(): ValidatedConfig {
        return ValidatedConfig(
            inputPath = Path.of(inputPath).also { it.checkExistence("inputPath") },
            outputPath = Path.of(outputPath).also { it.checkExistence("outputPath") },
            traits = traits.map { it.validated() },
        )
    }

    class ValidatedConfig(
        val inputPath: Path,
        val outputPath: Path,
        val traits: List<TraitConfig.ValidatedTraitConfig>,
    )

}

@Serializable
data class TraitConfig(
    val traitName: String,
    val variants: List<String>
) {
    fun validated(): ValidatedTraitConfig {
        return ValidatedTraitConfig(
            name = TraitName(traitName).also { traitName.checkIfBlank("name") },
            variants = variants.map { variant -> VariantName(variant)
                .also { traitName.checkIfBlank("$traitName: $variant") } }
        )
    }

    class ValidatedTraitConfig(
        val name: TraitName,
        val variants: List<VariantName>
    )
}

private object Errors {
    private fun configError(text: String): Nothing = error("[Error] $text")
    fun blankPropError(propName: String): Nothing = configError("$propName is blank")
    fun pathNotExists(propName: String): Nothing = configError("Path is referenced by $propName is not exists")
}

private fun Path.checkExistence(propName: String) {
    if (this.notExists()) Errors.pathNotExists(propName)
}

private fun String.checkIfBlank(propName: String) {
    if (this.isBlank()) Errors.blankPropError(propName)
}
