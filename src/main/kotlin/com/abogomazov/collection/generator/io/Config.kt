package com.abogomazov.collection.generator.io

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val inputPath: String,
    val outputPath: String,
    val traits: List<TraitConfig>,
)

@Serializable
data class TraitConfig(
    val name: String,
    val variants: List<String>
)
