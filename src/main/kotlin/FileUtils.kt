import com.soywiz.korim.bitmap.Bitmap32
import java.awt.Transparency
import java.awt.image.*
import java.io.File
import javax.imageio.ImageIO

private var outputFileIndex = 0

fun renderImage(bitmap: Bitmap32) {
    val bytes = bitmap.extractBytes()
    val raster = Raster.createInterleavedRaster(
        DataBufferByte(bytes, bytes.size),
        WIDTH, HEIGHT, 4 * WIDTH, 4, intArrayOf(0, 1, 2, 3), null
    )
    val image = BufferedImage(
        ComponentColorModel(
            ColorModel.getRGBdefault().colorSpace,
            true,
            true,
            Transparency.OPAQUE,
            DataBuffer.TYPE_BYTE), raster, true, null)

    ImageIO.write(image, "png", File("$OUTPUT_PATH/output_${outputFileIndex++}.png"))
}

fun loadImage(path: String): Raster = ImageIO.read(File(path)).data

fun path(basePath: String = BASE_PATH, traitType: String, id: Long, format: String = "png") =
    "$basePath/${traitType}_$id.$format"
