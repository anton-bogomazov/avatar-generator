package generator.domain

data class GeneratedImage private constructor(
    val name: GeneratedImageName,
    private val bitmap: GeneratedImageBitmap
) {

    companion object {
        fun of(name: GeneratedImageName, bitmap: GeneratedImageBitmap) = GeneratedImage(name, bitmap)
        fun init(traitName: TraitName, variant: TraitVariant): GeneratedImage =
            GeneratedImage(
                GeneratedImageName.of(traitName, variant.name),
                variant.image.toGeneratedImageBitmap()
            )
    }

    fun render() = bitmap.render()

    // todo skip transparent pixels? move to RGB?
    fun appendTrait(traitName: TraitName, variant: TraitVariant): GeneratedImage {
        val name = this.name + GeneratedImageName.of(traitName, variant.name)

        return of(name, bitmap.draw(variant.image))
    }

    fun getPixel(x: Int, y: Int) = bitmap.getPixel(x, y)

}

@JvmInline
value class GeneratedImageName private constructor(private val value: String) {

    companion object {
        fun of(value: String) = GeneratedImageName(value)
        fun of(traitName: TraitName, variantName: TraitVariantName) =
            GeneratedImageName("${traitName}_${variantName}")
    }

    override fun toString() = value

    operator fun plus(of: GeneratedImageName) = of("${this.value}-${of.value}")

}
