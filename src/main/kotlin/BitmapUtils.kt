import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.color.RGBA
import java.awt.image.Raster

fun createBitmap(pixels: Raster): Bitmap32 {
    val bitmap = Bitmap32(WIDTH, HEIGHT)
    val temp = IntArray(4)

    mapMatrix { i, j ->
        val pixel = pixels.getPixel(i, j, temp)
        if (pixel.sum() != 0) {
            bitmap.drawPixelMixed(i, j, RGBA(pixel[0], pixel[1], pixel[2], pixel[3]))
        }
    }

    return bitmap
}

fun Bitmap32.appendTrait(trait: Bitmap32): Bitmap32 =
    mapMatrix { i, j -> this.drawPixelMixed(i, j, trait[i, j]) }.let { this }


fun mapMatrix(mapper: (Int, Int) -> Unit) {
    for (i in 0 until HEIGHT) {
        for (j in 0 until WIDTH) {
            mapper(i, j)
        }
    }
}
