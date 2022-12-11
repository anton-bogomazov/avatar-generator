package api.gui

import api.gui.component.NftCollectionFrame
import generator.domain.Trait

val traits: MutableList<Trait> = mutableListOf()

fun main() {
    NftCollectionFrame.revalidate()
}
