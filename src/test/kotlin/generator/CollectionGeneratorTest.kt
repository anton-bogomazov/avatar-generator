package generator

import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.color.RGBA
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.*
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import generator.domain.*

class CollectionGeneratorTest : FunSpec({

    test("generate collection empty list") {
        CollectionGenerator.generate(listOf()).shouldBeEmpty()
    }

    test("generate collection of images from list of traits") {
        val traits = listOf(
            trait(TraitName("head"), listOf(TraitVariantName("iron"), TraitVariantName("giant"))),
            trait(TraitName("body"), listOf(TraitVariantName("small"), TraitVariantName("sparkling"), TraitVariantName("pink"))),
            trait(TraitName("legs"), listOf(TraitVariantName("narrow"), (TraitVariantName("long"))))
        )

        val images = CollectionGenerator.generate(traits)

        assertSoftly {
            images shouldHaveSize 12
            images.forAny { it.name.value shouldBe "head_iron-body_small-legs_narrow" }
                .forAny { it.name.value shouldBe "head_giant-body_sparkling-legs_long" }
                .forAll { it.name.value.length shouldBeGreaterThan "head_iron-".length }
        }
    }

    test("generate collection check bitmap") {
        val traits = listOf(
            trait(TraitName("head"), listOf(TraitVariantName("iron")), 0),
            trait(TraitName("body"), listOf(TraitVariantName("small")), 1),
            trait(TraitName("legs"), listOf(TraitVariantName("narrow")), 2)
        )

        val images = CollectionGenerator.generate(traits)

        assertSoftly {
            images shouldHaveSize 1

            images.first().bitmap[0, 0] shouldBe RGBA(1)
            images.first().bitmap[0, 1] shouldBe RGBA(1)
            images.first().bitmap[0, 2] shouldBe RGBA(1)
        }
    }

})

fun trait(name: TraitName, variants: List<TraitVariantName>, markPixel: Int? = null) =
    Trait(name, variants.mapIndexed { i, variantName ->
        val bitmap = bitmap()
        if (markPixel != null) bitmap[i, markPixel] = RGBA(1)
        TraitVariant(variantName, TraitVariantBitmap(bitmap))
    })

fun bitmap() = Bitmap32(12, 12)
