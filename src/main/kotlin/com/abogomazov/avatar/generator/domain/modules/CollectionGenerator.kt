package com.abogomazov.avatar.generator.domain.modules

import com.abogomazov.avatar.generator.domain.GeneratedImage
import com.abogomazov.avatar.generator.domain.GeneratedImageName
import com.abogomazov.avatar.generator.domain.Trait


object CollectionGenerator {

    fun generate(traits: List<Trait>): List<GeneratedImage> {
        if (traits.isEmpty()) return emptyList()

        val firstTrait = traits.first()
        var images = firstTrait.variants.map { variant ->
            GeneratedImage(GeneratedImageName.of(firstTrait.name, variant.name), variant.image)
        }

        for (trait in (traits - firstTrait)) {
            images = images.flatMap { image ->
                trait.variants.map { variant ->
                    image.appendTrait(trait.name, variant)
                }
            }
        }

        return images
    }

}
