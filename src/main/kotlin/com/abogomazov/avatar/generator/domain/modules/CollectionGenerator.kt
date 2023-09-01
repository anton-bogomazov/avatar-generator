package com.abogomazov.avatar.generator.domain.modules

import com.abogomazov.avatar.generator.domain.GeneratedImage
import com.abogomazov.avatar.generator.domain.GeneratedImageName
import com.abogomazov.avatar.generator.domain.Trait


object CollectionGenerator {

    fun generate(traits: List<Trait>): List<GeneratedImage> {
        if (traits.isEmpty()) return emptyList()

        var images = initialImages(traits.first())

        for (trait in (traits - traits.first())) {
            images = images.flatMap { image ->
                trait.variants.map { variant ->
                    image.appendTrait(trait.name, variant)
                }
            }
        }

        return images
    }

    private fun initialImages(initialTrait: Trait) =
        initialTrait.variants.map { variant ->
            GeneratedImage(GeneratedImageName.of(initialTrait.name, variant.name), variant.image)
        }

}
