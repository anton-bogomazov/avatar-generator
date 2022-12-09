package api

import app.HEIGHT
import app.WIDTH
import generator.domain.Trait
import generator.domain.TraitName
import processor.FileTraitImportProcessor
import processor.domain.FileName
import processor.domain.Path
import processor.domain.TraitVariantFile

// todo implement state machine
class CliApi {

    fun read(): List<Trait> {
        val traits = mutableListOf<Trait>()

        while (true) {
            println("> Base path: ")
            val basePath = Path(readln())
            println("> height width: ")
            val (height, width) = readln().split(' ').map { it.toInt() }
            HEIGHT = height
            WIDTH = width

            println("> Import new trait? (y/n) ")
            val control = readln().lowercase()
            if (control == "n") break

            println("> Trait name: ")
            val traitName = TraitName(readln())

            println("> List of files (only names, w/o extension): ")
            val files = readln().split(' ').map { TraitVariantFile(basePath, FileName(it)) }

            // todo run import async here
            traits.add(FileTraitImportProcessor(traitName, files).process())
        }

        if (traits.isEmpty()) return listOf()

        println("> Be ready to save ${countAmountOfTokens(traits)} tokens! Let's start? (y/n) ")
        val control = readln().lowercase()
        if (control == "n") return listOf()

        return traits
    }

    private fun countAmountOfTokens(traits: List<Trait>): Long {
        var amountOfTokens = 0L

        for (trait in traits) {
            if (amountOfTokens == 0L) {
                amountOfTokens = 1
            }
            amountOfTokens *= trait.variants.size
        }

        return amountOfTokens
    }

}
