package generator.domain

import com.soywiz.korim.bitmap.Bitmap32
import java.awt.Transparency
import java.awt.image.*

data class GeneratedImage(
    val name: GeneratedImageName,
    val bitmap: Bitmap32
) {

    fun render(): BufferedImage {
        val bytes = bitmap.extractBytes()
        val raster = Raster.createInterleavedRaster(
            DataBufferByte(bytes, bytes.size),
            bitmap.width, bitmap.height,
            4 * bitmap.width, 4, intArrayOf(0, 1, 2, 3), null)
        return BufferedImage(
            ComponentColorModel(
                ColorModel.getRGBdefault().colorSpace,
                true,
                true,
                Transparency.OPAQUE,
                DataBuffer.TYPE_BYTE), raster, true, null)
    }

    override fun toString(): String {
        return "GeneratedImage(name=${name.value})"
    }

}

@JvmInline
value class GeneratedImageName(val value: String)
