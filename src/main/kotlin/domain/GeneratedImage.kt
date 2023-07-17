package domain

import adapter.Bitmap

class GeneratedImage private constructor(
    val name: GeneratedImageName,
    private val bitmap: Bitmap
) {

    companion object {
        fun of(name: GeneratedImageName, bitmap: Bitmap) = GeneratedImage(name, bitmap)
    }

    fun render() = bitmap.render()

    fun appendTrait(traitName: TraitName, variant: TraitVariant): GeneratedImage {
        return of(name.append(traitName, variant.name), bitmap.draw(variant.image))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GeneratedImage

        if (name != other.name) return false
        return bitmap == other.bitmap
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + bitmap.hashCode()
        return result
    }


}

class GeneratedImageName private constructor(private var value: String) {

    companion object {
        fun of(traitName: TraitName, variantName: VariantName) =
            GeneratedImageName("${traitName}_${variantName}")
    }

    override fun toString() = value

    fun append(traitName: TraitName, variantName: VariantName): GeneratedImageName {
        return GeneratedImageName("$value-${traitName}_${variantName}")
    }

}
