package io

import adapter.Bitmap
import domain.Trait
import domain.TraitName
import domain.TraitVariant
import domain.VariantName
import java.io.File
import javax.imageio.ImageIO


class TraitSourcesImporter(
    private val traitName: TraitName,
    private val path: String,
    private val variantFiles: List<String>,
    private val format: String = "png"
) {

    fun import() =
        Trait(traitName, variantFiles.map { filename ->
            TraitVariant(
                VariantName(filename),
                Bitmap.from(ImageIO.read(File("$path/$filename.$format"))))
        })

}
