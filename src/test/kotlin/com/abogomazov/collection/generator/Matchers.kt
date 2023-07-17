package com.abogomazov.collection.generator

import java.awt.image.BufferedImage

fun BufferedImage.compareImages(other: BufferedImage): Boolean {
    if (this.width != other.width || this.height != other.height) {
        return false
    }
    val width = this.width
    val height = this.height

    for (y in 0 until height) {
        for (x in 0 until width) {
            if (this.getRGB(x, y) != other.getRGB(x, y)) {
                return false
            }
        }
    }
    return true
}
