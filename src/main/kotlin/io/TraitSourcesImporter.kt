package io

import adapter.Bitmap
import domain.Trait
import domain.TraitName
import domain.TraitVariant
import domain.VariantName
import java.io.File
import javax.imageio.ImageIO


class TraitSourcesImporter(
    private val traits: List<io.Trait>,
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
