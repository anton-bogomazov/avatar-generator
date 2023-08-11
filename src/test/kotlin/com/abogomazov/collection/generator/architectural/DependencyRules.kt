package com.abogomazov.collection.generator.architectural

import com.abogomazov.collection.generator.factories.AdapterFactories
import com.abogomazov.collection.generator.adapter.io.IO
import com.abogomazov.collection.generator.adapter.korim.KorimBitmap
import com.abogomazov.collection.generator.app.App
import com.abogomazov.collection.generator.domain.Bitmap
import com.abogomazov.collection.generator.domain.modules.ImageReader
import com.abogomazov.collection.generator.domain.modules.ImageWriter
import io.kotest.core.spec.style.StringSpec
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.library.Architectures.onionArchitecture


class DependencyRules : StringSpec({

    val projectRoot = "com.abogomazov.collection.generator"

    "hexagonal architecture is implemented" {
        val classes = ClassFileImporter()
            .withImportOption(DoNotIncludeTests())
            .importPackages(projectRoot)

        onionArchitecture()
            .domainModels("..domain..")
            .domainServices("..domain.modules..")
            .applicationServices("..app..")
            .adapter("korim bitmap", "..adapter.korim..")
            .adapter("io", "..adapter.io..")

            // AdapterFactories is a point of tension because of intentionally lacking DI.
            .ignoreDependency(App::class.java, AdapterFactories::class.java)
            .ignoreDependency(AdapterFactories::class.java, IO::class.java)
            .ignoreDependency(AdapterFactories::class.java, KorimBitmap::class.java)
            .ignoreDependency(AdapterFactories::class.java, KorimBitmap.Companion::class.java)
            .ignoreDependency(AdapterFactories::class.java, ImageWriter::class.java)
            .ignoreDependency(AdapterFactories::class.java, ImageReader::class.java)
            .ignoreDependency(AdapterFactories::class.java, Bitmap::class.java)

            .check(classes)
    }

})
