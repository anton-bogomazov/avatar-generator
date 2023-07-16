package adapter

import compareImages
import image
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.awt.Color

class BitmapTest : StringSpec({

    "drawing another bitmap do not mutate original one and creates a new" {
        val original = Bitmap.from(image(0 to 0, Color.black))
        val bitmapToDraw = Bitmap.from(image(1 to 1, Color.black))

        val result = original.draw(bitmapToDraw)

        result shouldNotBe original // initial image was changed
        result shouldNotBe bitmapToDraw // initial image wasn't completely redrawn by bitmapToDraw
    }

    "new bitmap overlaps original one if pixel's alpha > 0" {
        val position = 0 to 0
        val original = Bitmap.from(image(position, Color.black))
        val bitmapToDraw = Bitmap.from(image(position, Color.red))

        val result = original.draw(bitmapToDraw)

        result shouldBe bitmapToDraw
    }

    "render bitmap32 value to buffered image" {
        val expected = image(0 to 0, Color.black)

        val sut = Bitmap.from(expected)

        sut.render().compareImages(expected) shouldBe true
    }

})
