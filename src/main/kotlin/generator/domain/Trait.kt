package generator.domain

import com.soywiz.korim.bitmap.Bitmap32

data class Trait(
    val name: TraitName,
    val variants: List<TraitVariant>
)

@JvmInline
value class TraitName(val value: String) {
    override fun toString(): String {
        return value
    }
}

data class TraitVariant(val name: TraitVariantName, val image: TraitVariantBitmap)

@JvmInline
value class TraitVariantBitmap(val value: Bitmap32)

@JvmInline
value class TraitVariantName(val value: String)
