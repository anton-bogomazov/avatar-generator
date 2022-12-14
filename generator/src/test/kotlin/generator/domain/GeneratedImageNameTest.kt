package generator.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GeneratedImageNameTest : StringSpec({

    "of - should return instance with value generated from trait/variant names separated by underscore" {
        GeneratedImageName.of(
            TraitName("TRAIT"), TraitVariantName("VARIANT")
        ).toString() shouldBe "TRAIT_VARIANT"
    }

    "toString - should return string representation of the value" {
        GeneratedImageName.of("TEST").toString() shouldBe "TEST"
    }

    "plus - should append new value separated by stash" {
        (GeneratedImageName.of("FIRST") + GeneratedImageName.of("TEST")).toString() shouldBe "FIRST-TEST"
    }
})
