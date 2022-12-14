package generator.domain

import com.soywiz.korim.bitmap.Bitmap32
import generator.domain.util.toBitmap32
import util.mapMatrix
import java.awt.Transparency
import java.awt.image.*

abstract class Bitmap(protected val value: Bitmap32) {

    //todo review later
    fun render(): BufferedImage {
        val bytes = value.extractBytes()
        val raster = Raster.createInterleavedRaster(
            DataBufferByte(bytes, bytes.size),
            value.width, value.height,
            4 * value.width, 4, intArrayOf(0, 1, 2, 3), null)
        return BufferedImage(
            ComponentColorModel(
                ColorModel.getRGBdefault().colorSpace,
                true,
                true,
                Transparency.OPAQUE,
                DataBuffer.TYPE_BYTE), raster, true, null)
    }

}

class TraitVariantBitmap private constructor(value: Bitmap32) : Bitmap(value) {

    companion object {
        fun of(value: Bitmap32) = TraitVariantBitmap(value)
        fun from(value: Raster) = TraitVariantBitmap(value.toBitmap32())
    }

    fun getPixel(i: Int, j: Int) = value[i, j]

    fun toGeneratedImageBitmap() = GeneratedImageBitmap.from(value)

}

class GeneratedImageBitmap private constructor(value: Bitmap32) : Bitmap(value) {

    companion object {
        fun from(value: Bitmap32) = GeneratedImageBitmap(value)
    }

    fun draw(variant: TraitVariantBitmap): GeneratedImageBitmap {
        val newImage = value.clone()

        mapMatrix(value.height, value.width) { i, j ->
            newImage.drawPixelMixed(i, j, variant.getPixel(i, j))
        }

        return from(newImage)
    }

    fun getPixel(x: Int, y: Int) = value.getRgba(x, y)

}
