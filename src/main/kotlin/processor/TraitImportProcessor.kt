package processor

import app.HEIGHT
import app.WIDTH
import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.color.RGBA
import generator.domain.Trait
import generator.domain.TraitName
import processor.domain.InputTraitVariantData
import java.awt.image.Raster

abstract class TraitImportProcessor(
    val traitName: TraitName,
    open val inputData: List<InputTraitVariantData>
) {

    abstract fun process(): Trait

    protected fun createBitmap(pixels: Raster): Bitmap32 {
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

    private fun mapMatrix(mapper: (Int, Int) -> Unit) {
        for (i in 0 until HEIGHT) {
            for (j in 0 until WIDTH) {
                mapper(i, j)
            }
        }
    }

}
