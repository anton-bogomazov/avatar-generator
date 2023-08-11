package com.abogomazov.avatar.generator.domain

import java.awt.image.BufferedImage


interface Bitmap {
    fun render(): BufferedImage
    fun draw(bitmap: Bitmap): Bitmap
}
