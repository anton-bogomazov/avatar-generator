package com.abogomazov.collection.generator.domain

import com.abogomazov.collection.generator.generatedImage
import com.abogomazov.collection.generator.image
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import com.abogomazov.collection.generator.trait
import com.abogomazov.collection.generator.variants

class GeneratedImageTest : StringSpec({

    "appending trait results in drawing new bitmap and appending name" {
        val sut = generatedImage(bufImage = image())
        val trait = trait("newtrait", variants("big", "small"))

        val result = sut.appendTrait(trait.name, trait.variants.first())

        result.name.toString() shouldBe "trait_variant-newtrait_big"
    }

})

