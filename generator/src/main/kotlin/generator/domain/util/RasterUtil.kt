package generator.domain.util

import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.color.RGBA
import util.mapMatrix
import java.awt.image.Raster

fun Raster.toBitmap32(): Bitmap32 {
    val bitmap = Bitmap32(this.width, this.height)
    val temp = IntArray(4)

    mapMatrix(this.height, this.width) { i, j ->
        val pixel = this.getPixel(i, j, temp)
        if (pixel.sum() != 0) {
            bitmap.drawPixelMixed(i, j, RGBA(pixel[0], pixel[1], pixel[2], pixel[3]))
        }
    }

    return bitmap
}
