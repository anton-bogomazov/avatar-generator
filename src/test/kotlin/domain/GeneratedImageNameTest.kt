package domain

import imageName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GeneratedImageNameTest : StringSpec({

    "of - should return instance with value generated from trait/variant names separated by underscore" {
        imageName("TRAIT", "VARIANT").toString() shouldBe "TRAIT_VARIANT"
    }

    "traits are separated by stash" {
        imageName("TRAIT1", "VARIANT1")
            .append(TraitName("TRAIT2"), VariantName("VARIANT2")).toString() shouldBe "TRAIT1_VARIANT1-TRAIT2_VARIANT2"
    }

})
