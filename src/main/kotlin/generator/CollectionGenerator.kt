package generator

import app.HEIGHT
import app.WIDTH
import com.soywiz.korim.bitmap.Bitmap32
import generator.domain.GeneratedImage
import generator.domain.GeneratedImageName
import generator.domain.Trait

object CollectionGenerator {

    fun generate(traits: List<Trait>): List<GeneratedImage> {
        val images = hashMapOf<GeneratedImage, Int>()

        traits.forEachIndexed { traitIndex, trait ->
            trait.variants.forEach { variant ->
                val key = "${trait.name}_${variant.name}"
                val withoutCurrentTrait = images.filter { !it.key.name.value.contains(trait.name.value) }

                if (traitIndex == 0) {
                    images[GeneratedImage(GeneratedImageName(key), variant.image.value)] = 1
                } else {
                    withoutCurrentTrait.forEach { image ->
                        // fixme bitmap low level, abstraction mismatch
                        val updatedBitmap = image.key.bitmap.appendTrait(variant.image.value)
                        val generatedImage = GeneratedImage(
                            GeneratedImageName("${image.key.name.value}-$key"), updatedBitmap)
                        images[generatedImage] = image.value + 1
                    }
                }
            }
        }

        return images.filter { it.value == traits.size }.keys.toList()
    }

}

fun Bitmap32.appendTrait(trait: Bitmap32): Bitmap32 =
    mapMatrix { i, j -> this.drawPixelMixed(i, j, trait[i, j]) }.let { this }

// fixme duplication
private fun mapMatrix(mapper: (Int, Int) -> Unit) {
    for (i in 0 until HEIGHT) {
        for (j in 0 until WIDTH) {
            mapper(i, j)
        }
    }
}
