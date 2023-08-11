package com.abogomazov.avatar.generator.app

import com.abogomazov.avatar.generator.validConfig
import com.abogomazov.avatar.generator.validTraitConfig
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


class ConfigValidationTest : StringSpec({

    "error if input path is not existing" {
        shouldThrow<IllegalStateException> {
            validConfig(inputPath = "not-exists").validated()
        }.message shouldBe "[Error] Path is referenced by inputPath is not exists"
    }

    "error if input path is blank" {
        shouldThrow<IllegalStateException> {
            validConfig(inputPath = "").validated()
        }.message shouldBe "[Error] inputPath is blank"
    }

    "error if trait name is blank" {
        shouldThrow<IllegalStateException> {
            validTraitConfig(name = "").validated()
        }.message shouldBe "[Error] trait name is blank"
    }

    "error if variant name is blank" {
        shouldThrow<IllegalStateException> {
            validTraitConfig(variants = listOf("first", "")).validated()
        }.message shouldBe "[Error] variant name for trait=trait is blank"
    }

    "error if no variants for trait" {
        shouldThrow<IllegalStateException> {
            validTraitConfig(variants = emptyList()).validated()
        }.message shouldBe "[Error] variants of trait=trait has no elements"
    }

})
