package io

import domain.TraitName
import image
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.io.File
import java.nio.file.Files
import javax.imageio.ImageIO
import kotlin.io.path.Path

class TraitSourcesImporterTest : StringSpec({

    val inputPath = "input"
    val variants = setOf("big", "small")

    beforeTest {
        if (!Files.exists(Path(inputPath))) {
            Files.createDirectories(Path(inputPath))
        }
        variants.forEach { ImageIO.write(image(), "png", File("$inputPath/$it.png")) }
    }

    afterTest {
        variants.forEach { Files.deleteIfExists(Path("$inputPath/$it.png")) }
        Files.deleteIfExists(Path(inputPath))
    }

    "imports files from defined directory" {
        val sut = TraitSourcesImporter(TraitName("trait"), inputPath, variants.toList())

        val result = sut.import()

        with(result) {
            name shouldBe TraitName("trait")
            this.variants shouldHaveSize 2
            this.variants.map { it.name.toString() }.toSet() shouldBe variants
        }
    }

})
