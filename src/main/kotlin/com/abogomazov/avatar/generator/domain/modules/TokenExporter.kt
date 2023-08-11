package com.abogomazov.avatar.generator.domain.modules

import com.abogomazov.avatar.generator.domain.GeneratedImage
import java.awt.image.BufferedImage


class TokenExporter(
    private val writer: ImageWriter
) {

    fun write(image: GeneratedImage) {
        writer.write(image.render(), image.name.toString())
    }

}

fun interface ImageWriter {
    fun write(image: BufferedImage, filename: String)
}
