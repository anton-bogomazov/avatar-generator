package com.abogomazov.avatar.generator.domain.modules

import com.abogomazov.avatar.generator.domain.TraitImportInfo
import com.abogomazov.avatar.generator.domain.Trait
import com.abogomazov.avatar.generator.domain.TraitName
import com.abogomazov.avatar.generator.domain.VariantName
import com.abogomazov.avatar.generator.domain.TraitVariant
import com.abogomazov.avatar.generator.domain.Bitmap


class TraitSourcesImporter(
    private val traits: List<TraitImportInfo>,
    private val reader: ImageReader
) {

    fun import(): List<Trait> {
        return traits.filter { it.variantNames.isNotEmpty() }
            .map { importTrait(it.traitName, it.variantNames) }
    }

    private fun importTrait(traitName: TraitName, variantNames: List<VariantName>) =
        Trait(
            name = traitName,
            variants = variantNames.map { name ->
                TraitVariant(name, reader.read(name.toString()))
            }
        )

}

fun interface ImageReader {
    fun read(filename: String): Bitmap
}
