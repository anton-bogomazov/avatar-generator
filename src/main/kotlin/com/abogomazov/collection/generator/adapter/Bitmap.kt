package com.abogomazov.collection.generator.adapter

import com.soywiz.korim.awt.toAwt
import com.soywiz.korim.awt.toBMP32
import com.soywiz.korim.bitmap.Bitmap32
import java.awt.image.*


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
