package com.abogomazov.avatar.generator.factories

import com.abogomazov.avatar.generator.domain.Bitmap
import com.abogomazov.avatar.generator.domain.modules.ImageReader
import com.abogomazov.avatar.generator.domain.modules.ImageWriter
import com.abogomazov.avatar.generator.adapter.io.IO
import com.abogomazov.avatar.generator.adapter.korim.KorimBitmap
import java.awt.image.BufferedImage
import java.nio.file.Path

object AdapterFactories {
    fun instantiateReader(path: Path): ImageReader = IO(path)
    fun instantiateWriter(path: Path): ImageWriter = IO(path)
    
    fun createBitmap(image: BufferedImage): Bitmap = KorimBitmap.from(image)
}
