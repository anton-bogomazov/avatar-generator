package com.abogomazov.collection.generator.adapter

import korlibs.image.awt.toAwt
import korlibs.image.awt.toBMP32
import korlibs.image.bitmap.Bitmap32
import java.awt.image.BufferedImage


class Bitmap private constructor(private val value: Bitmap32) {

    companion object {
        fun from(value: BufferedImage) = Bitmap(value.toBMP32())
    }

    fun draw(bitmap: Bitmap): Bitmap {
        val newImage = value.clone()
        newImage.draw(bitmap.value)

        return Bitmap(newImage)
    }

    fun render() = this.value.toAwt()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bitmap

        return value.contentEquals(other.value)
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }

}
