package com.abogomazov.collection.generator.app.modules

import com.abogomazov.collection.generator.app.io.IO
import com.abogomazov.collection.generator.domain.TraitName
import com.abogomazov.collection.generator.image
import com.abogomazov.collection.generator.traitConfig
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk


class TraitSourcesImporterTest : StringSpec({

    val io = mockk<IO>()
    every { io.read(any()) } returns image()

    "imports files from defined directory" {
        val variants = listOf("big", "small")
        val traitName = "trait"
        val sut = TraitSourcesImporter(traitConfig(traitName, variants = variants), io)

        val result = sut.import()

        with(result.first()) {
            name shouldBe TraitName(traitName)
            this.variants shouldHaveSize 2
            this.variants.map { it.name.toString() }.toSet() shouldBe variants
        }
    }

})
