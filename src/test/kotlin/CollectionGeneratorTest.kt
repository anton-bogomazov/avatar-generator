import app.CollectionGenerator
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldHaveSize

class CollectionGeneratorTest : StringSpec({

    "nothing are produced when no traits" {
        CollectionGenerator.generate(emptyList()).shouldBeEmpty()
    }

    "traits are applied in the proper order to generate a set of images" {
        val traits = listOf(
            trait("head", variants("iron", "giant")),
            trait("body", variants("small", "sparkling", "pink")),
            trait("legs", variants("narrow", "long"))
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
