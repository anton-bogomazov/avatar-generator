package com.abogomazov.collection.generator.factories

import com.abogomazov.collection.generator.domain.Bitmap
import com.abogomazov.collection.generator.domain.modules.ImageReader
import com.abogomazov.collection.generator.domain.modules.ImageWriter
import com.abogomazov.collection.generator.adapter.io.IO
import com.abogomazov.collection.generator.adapter.korim.KorimBitmap
import java.awt.image.BufferedImage
import java.nio.file.Path

object AdapterFactories {
    fun instantiateReader(path: Path): ImageReader = IO(path)
    fun instantiateWriter(path: Path): ImageWriter = IO(path)
    
    fun createBitmap(image: BufferedImage): Bitmap = KorimBitmap.from(image)
}
