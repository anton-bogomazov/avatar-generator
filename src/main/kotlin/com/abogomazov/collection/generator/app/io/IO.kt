package com.abogomazov.collection.generator.app.io

import com.abogomazov.collection.generator.app.modules.ImageReader
import com.abogomazov.collection.generator.app.modules.ImageWriter
import com.abogomazov.collection.generator.domain.ImageFormat
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

    override fun read(filename: String): BufferedImage {
        val file = File("$path/$filename.$format")
        if (file.toPath().notExists()) error("[IO ERROR] $file is not exists")
        return ImageIO.read(File("$path/$filename.$format"))
    }
}
