package com.abogomazov.collection.generator.io

import com.abogomazov.collection.generator.domain.GeneratedImage
import java.io.File
import java.nio.file.Files
import javax.imageio.ImageIO
import kotlin.io.path.Path


class TokenExporter(
    private val path: String,
    private val format: String = "png"
) {

    fun writeGeneratedImage(image: GeneratedImage) {
        if (!Files.exists(Path(path))) {
            Files.createDirectories(Path(path))
        }
        val file = File("${path}/${image.name}.${format}")
        ImageIO.write(image.render(), format, file)
    }

}
