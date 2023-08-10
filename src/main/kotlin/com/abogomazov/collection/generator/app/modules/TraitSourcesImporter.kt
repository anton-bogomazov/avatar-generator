package com.abogomazov.collection.generator.app.modules

import com.abogomazov.collection.generator.adapter.Bitmap
import com.abogomazov.collection.generator.app.TraitConfig
import com.abogomazov.collection.generator.domain.Trait
import com.abogomazov.collection.generator.domain.TraitName
import com.abogomazov.collection.generator.domain.TraitVariant
import com.abogomazov.collection.generator.domain.VariantName


import java.awt.image.BufferedImage


class TraitSourcesImporter(
    private val traits: List<TraitConfig.ValidatedTraitConfig>,
    private val reader: ImageReader
) {

    fun import(): List<Trait> {
        return traits.filter { it.variants.isNotEmpty() }
            .map { importTrait(it.name, it.variants) }
    }

    private fun importTrait(traitName: TraitName, variantNames: List<VariantName>) =
        Trait(traitName, variantNames.map { name ->
            TraitVariant(
                name,
                Bitmap.from(reader.read(name.toString()))
            )
        })

}

fun interface ImageReader {
    fun read(filename: String): BufferedImage
}
