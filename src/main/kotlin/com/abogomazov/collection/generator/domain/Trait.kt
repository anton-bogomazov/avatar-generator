package com.abogomazov.collection.generator.domain

import com.abogomazov.collection.generator.adapter.Bitmap

data class Trait(
    val name: TraitName,
    val variants: List<TraitVariant>
)

data class TraitVariant(
    val name: VariantName,
    val image: Bitmap
)

@JvmInline
value class TraitName(private val value: String) {
    override fun toString() = value
}

@JvmInline
value class VariantName(private val value: String) {
    override fun toString() = value
}
