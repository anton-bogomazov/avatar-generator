package io

import adapter.Bitmap
import domain.GeneratedImage
import domain.GeneratedImageName
import domain.TraitName
import domain.VariantName
import image
import imageName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.nio.file.Files
import kotlin.io.path.Path

class TokenExporterTest : StringSpec({
    val outputPath = "output"

    beforeTest {
        Files.deleteIfExists(Path("$outputPath/trait_variant.png"))
        Files.deleteIfExists(Path(outputPath))
    }

    "if not exists directory for output files will be created" {
        val sut = TokenExporter(outputPath)
        val image = GeneratedImage.of(imageName("trait", "variant"), Bitmap.from(image()))

        sut.writeGeneratedImage(image)

        Files.exists(Path(outputPath)) shouldBe true
        Files.exists(Path("$outputPath/trait_variant.png")) shouldBe true
    }

})
