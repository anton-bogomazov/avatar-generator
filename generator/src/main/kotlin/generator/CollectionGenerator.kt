package generator

import generator.domain.GeneratedImage
import generator.domain.Trait

object CollectionGenerator {

    /**
     * Generates a list of GeneratedImages based on the given list of Traits.
     *
     * @param traits a list of Traits to use for generating the images
     * @return a list of GeneratedImage generated from the given Traits, or an empty list if the input list is empty
     */
    fun generate(traits: List<Trait>) =
        traits.ifEmpty { return emptyList<GeneratedImage>() }
            .drop(1)
            .fold(traits.first().variants.map { GeneratedImage.init(traits.first().name, it) }) { images, trait ->
            images.flatMap { image ->
                trait.variants.map { variant ->
                    image.appendTrait(trait.name, variant)
                }
            }
        }

}
