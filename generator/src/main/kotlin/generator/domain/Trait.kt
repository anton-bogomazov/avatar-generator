package generator.domain

data class Trait(val name: TraitName, val variants: List<TraitVariant>)

data class TraitVariant(val name: TraitVariantName, val image: TraitVariantBitmap)

@JvmInline
value class TraitName(private val value: String) {
    override fun toString() = value
}

@JvmInline
value class TraitVariantName(private val value: String) {
    override fun toString() = value
}
