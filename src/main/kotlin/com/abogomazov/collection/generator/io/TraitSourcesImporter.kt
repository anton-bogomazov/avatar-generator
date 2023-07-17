package com.abogomazov.collection.generator.io

import com.abogomazov.collection.generator.adapter.Bitmap
import com.abogomazov.collection.generator.domain.Trait
import com.abogomazov.collection.generator.domain.TraitName
import com.abogomazov.collection.generator.domain.TraitVariant
import com.abogomazov.collection.generator.domain.VariantName
import java.io.File
import javax.imageio.ImageIO


class TraitSourcesImporter(
    private val traits: List<com.abogomazov.collection.generator.io.TraitConfig>,
    private val path: String,
    private val format: String = "png"
) {

    fun import() =
        traits.map {
            importTrait(TraitName(it.name), it.variants)
        }

    private fun importTrait(traitName: TraitName, variantFiles: List<String>) =
        Trait(traitName, variantFiles.map { filename ->
            TraitVariant(
                VariantName(filename),
                Bitmap.from(ImageIO.read(File("$path/$filename.$format"))))
        })

}
