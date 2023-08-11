package com.abogomazov.avatar.generator.integration

import com.abogomazov.avatar.generator.app.main
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import java.lang.IllegalStateException
import java.nio.file.Files
import javax.imageio.ImageIO
import kotlin.io.path.Path


class ApplicationTest : StringSpec({

    val resourcesPath = "src/test/resources"

    "happy path - 2 images successfully generated" {
        main(arrayOf("$resourcesPath/config.json"))

        val resultPath = Files.list(Path("$resourcesPath/results"))
        for (file in resultPath) {
            file.fileName.toString() shouldBeIn listOf(
                "first-form_yellow-square-second-form_green-triangle.PNG",
                "first-form_yellow-square-second-form_red-circle.PNG")
        }
        val image = ImageIO.read(
            Path("$resourcesPath/results/first-form_yellow-square-second-form_red-circle.PNG").toFile())

        // about the numbers https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values

        // part of yellow square
        image.getRGB(0, 0) shouldBe -3584
        // square overlapped by circle here
        image.getRGB(0, 2) shouldBe -1237980
        image.getRGB(0, 3) shouldBe -1237980
        // transparent background here
        image.getRGB(5, 5) shouldBe 0

        Files.deleteIfExists(Path("$resourcesPath/results/first-form_yellow-square-second-form_green-triangle.PNG"))
        Files.deleteIfExists(Path("$resourcesPath/results/first-form_yellow-square-second-form_red-circle.PNG"))
        Files.deleteIfExists(Path("$resourcesPath/results"))
    }

    "throw error and stop generation if variant source is not existing" {
        shouldThrow<IllegalStateException> {
            main(arrayOf("$resourcesPath/not-existing-variants-config.json"))
        }.message shouldBe "[IO ERROR] src/test/resources/images/not-exists.PNG is not exists"
    }

})
