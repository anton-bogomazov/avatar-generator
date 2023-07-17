import domain.GeneratedImage
import domain.GeneratedImageName
import domain.Trait

object CollectionGenerator {

    fun generate(traits: List<Trait>): List<GeneratedImage> {
        if (traits.isEmpty()) return emptyList()

        val firstTrait = traits.first()
        var images = firstTrait.variants.map { variant ->
            GeneratedImage.of(GeneratedImageName.of(firstTrait.name, variant.name), variant.image)
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
