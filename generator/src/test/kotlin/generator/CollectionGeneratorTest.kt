package generator

import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.color.RGBA
import generator.domain.*
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldHaveSize
import util.mapMatrix

class CollectionGeneratorTest : StringSpec({

    "should return empty list when given empty list of traits" {
        CollectionGenerator.generate(emptyList()).shouldBeEmpty()
    }

    "should return multiple images when given multiple traits with multiple variants" {
        val traits = listOf(
            trait(TraitName("head"), listOf(TraitVariantName("iron"), TraitVariantName("giant"))),
            trait(TraitName("body"), listOf(TraitVariantName("small"), TraitVariantName("sparkling"), TraitVariantName("pink"))),
            trait(TraitName("legs"), listOf(TraitVariantName("narrow"), (TraitVariantName("long"))))
        )

        val images = CollectionGenerator.generate(traits)

        assertSoftly {
            images shouldHaveSize 12
            images.forEach {
                it.name.toString() shouldBeIn listOf(
                    "head_iron-body_small-legs_narrow",
                    "head_iron-body_sparkling-legs_narrow",
                    "head_iron-body_pink-legs_narrow",
                    "head_iron-body_small-legs_long",
                    "head_iron-body_sparkling-legs_long",
                    "head_iron-body_pink-legs_long",
                    "head_giant-body_small-legs_narrow",
                    "head_giant-body_sparkling-legs_narrow",
                    "head_giant-body_pink-legs_narrow",
                    "head_giant-body_small-legs_long",
                    "head_giant-body_sparkling-legs_long",
                    "head_giant-body_pink-legs_long"
                )
            }
        }
    }

})

fun trait(name: TraitName, variantNames: List<TraitVariantName>) =
    Trait(name, variantNames.map { TraitVariant(it, TraitVariantBitmap.of(bitmap())) })

// fixme duplication
fun bitmap(color: RGBA): Bitmap32 {
    val bitmap = Bitmap32(12, 12)

    mapMatrix(12, 12) { x, y -> bitmap.drawPixelMixed(x, y, color) }

    return bitmap
}

fun bitmap() = Bitmap32(12, 12)
