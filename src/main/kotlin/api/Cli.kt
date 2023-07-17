package api

import domain.Trait
import domain.TraitName
import io.TraitSourcesImporter


object Cli {

    // TODO use json configuration except asking user
    fun read(): List<Trait> {
        val traits = mutableListOf<Trait>()

        while (true) {
            println("> Base path: ")
            val basePath = readln()
            println("> height <space> width: ")
            val (height, width) = readln().split(' ').map { it.toInt() }

            println("> Import new trait? (y/n) ")
            val control = readln().lowercase()
            if (control == "n") break

            println("> Trait name: ")
            val traitName = TraitName(readln())

            println("> List of files (only names, w/o extension): ")
            val files = readln().split(' ')

            traits.add(TraitSourcesImporter(traitName, basePath, files).import())
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
