package io

import generatedImage
import image
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
        val image = generatedImage(bufImage = image())

        sut.writeGeneratedImage(image)

        Files.exists(Path(outputPath)) shouldBe true
        Files.exists(Path("$outputPath/trait_variant.png")) shouldBe true
    }

})
