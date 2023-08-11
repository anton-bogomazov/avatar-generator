package com.abogomazov.avatar.generator.domain


data class GeneratedImage(
    val name: GeneratedImageName,
    private val bitmap: Bitmap
) {

    fun render() = bitmap.render()

    fun appendTrait(traitName: TraitName, variant: TraitVariant): GeneratedImage {
        return GeneratedImage(name.append(traitName, variant.name), bitmap.draw(variant.image))
    }

}

class GeneratedImageName private constructor(private var value: String) {

    companion object {
        fun of(traitName: TraitName, variantName: VariantName) =
            GeneratedImageName("${traitName}_${variantName}")
    }

    fun append(traitName: TraitName, variantName: VariantName): GeneratedImageName {
        return GeneratedImageName("$value-${traitName}_${variantName}")
    }

    override fun toString() = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GeneratedImageName

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}
