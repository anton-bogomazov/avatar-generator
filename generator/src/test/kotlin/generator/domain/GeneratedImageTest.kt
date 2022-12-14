package generator.domain

import com.soywiz.korim.color.Colors.BLACK
import com.soywiz.korim.color.Colors.RED
import com.soywiz.korim.color.RGBA
import generator.bitmap
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB

class GeneratedImageTest : StringSpec({

    "of - should create a GeneratedImage with the given name and bitmap" {
        val name = GeneratedImageName.of(TraitName("Trait"), TraitVariantName("Variant"))
        val bitmap = bitmap()
        val generatedImage = GeneratedImage.of(name, GeneratedImageBitmap.from(bitmap))

        generatedImage.name shouldBe name
    }

    "init - should create a GeneratedImage with the given trait name and variant" {
        val bitmap = bitmap()
        val traitName = TraitName("Trait")
        val variant = TraitVariant(
            TraitVariantName("Variant"),
            TraitVariantBitmap.of(bitmap)
        )
        val generatedImage = GeneratedImage.init(traitName, variant)
        generatedImage.name shouldBe GeneratedImageName.of(traitName, variant.name)
    }

    "getPixel - should return RGBA representation of pixel" {
        val image = image(RED)

        image.getPixel(0, 0) shouldBe RED
    }

    "render - should call bitmap.render" {
        val imageBitmapMock = mockk<GeneratedImageBitmap>()
        every { imageBitmapMock.render() } returns BufferedImage(10, 10, TYPE_INT_RGB)

        val image = GeneratedImage.of(GeneratedImageName.of("name"), imageBitmapMock)

        image.render()

        verify(exactly = 1) { imageBitmapMock.render() }
    }

    "appendTrait - should return bitmap with red pixel and don't mutate initial" {
        val image = image()
        val variant = markedBitmap(10, 10, RED)

        val result = image.appendTrait(TraitName("color"),
            TraitVariant(TraitVariantName("red"), TraitVariantBitmap.of(variant)))

        result.getPixel(10, 10) shouldBe RED
        image.getPixel(10, 10) shouldBe BLACK
    }

})

fun image(color: RGBA = BLACK) = GeneratedImage.init(TraitName("initTrait"),
    TraitVariant(TraitVariantName("black"), TraitVariantBitmap.of(bitmap(color))))

fun markedBitmap(x: Int, y: Int, color: RGBA) = bitmap().also { it.drawPixelMixed(x, y, color) }
