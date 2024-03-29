package com.abogomazov.avatar.generator

import com.abogomazov.avatar.generator.adapter.korim.KorimBitmap
import com.abogomazov.avatar.generator.app.Config
import com.abogomazov.avatar.generator.app.TraitConfig
import com.abogomazov.avatar.generator.domain.VariantName
import com.abogomazov.avatar.generator.domain.Trait
import com.abogomazov.avatar.generator.domain.TraitName
import com.abogomazov.avatar.generator.domain.TraitVariant
import com.abogomazov.avatar.generator.domain.GeneratedImageName
import com.abogomazov.avatar.generator.domain.GeneratedImage
import com.abogomazov.avatar.generator.domain.TraitImportInfo
import korlibs.image.color.toRgba
import java.awt.Color
import java.awt.image.BufferedImage


fun trait(name: String, variantNames: List<VariantName>) =
    Trait(TraitName(name), variantNames.map { TraitVariant(it, KorimBitmap.from(image(0 to 0, Color.black))) })

fun variants(vararg variants: String) = variants.map { VariantName(it) }

fun image(position: Pair<Int, Int> = 0 to 0, color: Color = Color.black): BufferedImage {
    val result = BufferedImage(12, 12, BufferedImage.TYPE_INT_ARGB)
    result.setRGB(position.first, position.second, color.toRgba().value)
    return result
}

fun imageName(traitName: String, variantName: String) =
    GeneratedImageName.of(TraitName(traitName), VariantName(variantName))

fun generatedImage(
    name: GeneratedImageName = imageName("trait", "variant"),
    bufImage: BufferedImage
) = GeneratedImage(name, KorimBitmap.from(bufImage))

fun traitImportInfo(
    vararg traitNames: String,
    variants: List<String> = listOf("variant1", "variant2")
) = traitNames.map { TraitImportInfo(TraitName(it), variants.map { VariantName(it) }) }

fun validConfig(
    inputPath: String = "input",
    outputPath: String = "output",
    traits: List<TraitConfig> = listOf(validTraitConfig()),
) = Config(
    inputPath = inputPath,
    outputPath = outputPath,
    traits = traits,
)

fun validTraitConfig(
    name: String = "trait",
    variants: List<String> = listOf("first", "second")
) = TraitConfig(
    name = name,
    variants = variants,
)
