package com.abogomazov.avatar.generator.adapter.io

import com.abogomazov.avatar.generator.factories.AdapterFactories
import com.abogomazov.avatar.generator.domain.Bitmap
import com.abogomazov.avatar.generator.domain.modules.ImageReader
import com.abogomazov.avatar.generator.domain.modules.ImageWriter
import com.abogomazov.avatar.generator.domain.ImageFormat
import java.awt.image.BufferedImage
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import javax.imageio.ImageIO
import kotlin.io.path.notExists


class IO(
    private val path: Path,
    private val format: ImageFormat = ImageFormat.PNG
) : ImageReader, ImageWriter {

    override fun write(image: BufferedImage, filename: String) {
        if (path.notExists()) {
            Files.createDirectories(path)
        }
        ImageIO.write(image, format.toString(), File("${path}/$filename.$format"))
    }

    override fun read(filename: String): Bitmap {
        val file = File("$path/$filename.$format")
        if (file.toPath().notExists()) error("[IO ERROR] $file is not exists")
        return AdapterFactories.createBitmap(ImageIO.read(File("$path/$filename.$format")))
    }
}
