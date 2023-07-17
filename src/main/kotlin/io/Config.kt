package io

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val inputPath: String,
    val outputPath: String,
    val traits: List<Trait>,
)

@Serializable
data class Trait(
    val name: String,
    val variants: List<String>
)
