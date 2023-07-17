import adapter.Bitmap
import com.soywiz.korim.color.toRgba
import domain.*
import java.awt.Color
import java.awt.image.BufferedImage


fun trait(name: String, variantNames: List<VariantName>) =
    Trait(TraitName(name), variantNames.map { TraitVariant(it, Bitmap.from(image(0 to 0, Color.black))) })

fun variants(vararg variants: String) = variants.map { VariantName(it) }

fun image(position: Pair<Int, Int> = 0 to 0, color: Color = Color.black): BufferedImage {
    val result = BufferedImage(12, 12, BufferedImage.TYPE_INT_ARGB)
    result.setRGB(position.first, position.second, color.toRgba().value)
    return result
}

fun BufferedImage.compareImages(other: BufferedImage): Boolean {
    if (this.width != other.width || this.height != other.height) {
        return false
    }
    val width = this.width
    val height = this.height

    for (y in 0 until height) {
        for (x in 0 until width) {
            if (this.getRGB(x, y) != other.getRGB(x, y)) {
                return false
            }
        }
    }
    return true
}

fun imageName(traitName: String, variantName: String) =
    GeneratedImageName.of(TraitName(traitName), VariantName(variantName))
