package com.abogomazov.collection.generator.adapter.korim

import com.abogomazov.collection.generator.domain.Bitmap
import korlibs.image.awt.toAwt
import korlibs.image.awt.toBMP32
import korlibs.image.bitmap.Bitmap32
import java.awt.image.BufferedImage


class KorimBitmap private constructor(private val value: Bitmap32): Bitmap {

    companion object {
        fun from(value: BufferedImage) = KorimBitmap(value.toBMP32())
    }

    override fun draw(bitmap: Bitmap): Bitmap {
        bitmap as KorimBitmap
        val newImage = value.clone()
        newImage.draw(bitmap.value)

        return KorimBitmap(newImage)
    }

    override fun render() = this.value.toAwt()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KorimBitmap

        return value.contentEquals(other.value)
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }

}
