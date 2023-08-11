package com.abogomazov.avatar.generator.domain.modules

import com.abogomazov.avatar.generator.adapter.io.IO
import com.abogomazov.avatar.generator.domain.TraitName
import com.abogomazov.avatar.generator.image
import com.abogomazov.avatar.generator.adapter.korim.KorimBitmap
import com.abogomazov.avatar.generator.traitImportInfo
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk


class TraitSourcesImporterTest : StringSpec({

    val io = mockk<IO>()
    every { io.read(any()) } returns KorimBitmap.from(image())

    "imports files from defined directory" {
        val variants = listOf("big", "small")
        val traitName = "trait"
        val sut = TraitSourcesImporter(traitImportInfo(traitName, variants = variants), io)

        val result = sut.import()

        with(result.first()) {
            name shouldBe TraitName(traitName)
            this.variants shouldHaveSize 2
            this.variants.map { it.name.toString() }.toSet() shouldBe variants
        }
    }

    "skip traits without variants" {
        val sut = TraitSourcesImporter(traitImportInfo("trait1", "trait2", variants = emptyList()), io)

        val result = sut.import()

        result shouldHaveSize 0
    }

})
