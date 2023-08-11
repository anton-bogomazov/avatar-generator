package com.abogomazov.avatar.generator.domain

import com.abogomazov.avatar.generator.imageName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GeneratedImageNameTest : StringSpec({

    "image name is trait and variant names separated by underscore" {
        imageName("TRAIT", "VARIANT").toString() shouldBe "TRAIT_VARIANT"
    }

    "trait_variant pairs are separated by stash" {
        imageName("TRAIT1", "VARIANT1")
            .append(TraitName("TRAIT2"), VariantName("VARIANT2")).toString() shouldBe
                "TRAIT1_VARIANT1-TRAIT2_VARIANT2"
    }

})
