package api.gui.component

import api.gui.abstraction.BaseFrame
import api.gui.component.button.AddTraitButton
import api.gui.component.button.RunGenerationButton
import api.gui.component.panel.TraitListPanel
import java.awt.BorderLayout

const val windowTitle = "NFT Collection Generator"
const val windowWidth = 500
const val windowHeight = 700

object NftCollectionFrame : BaseFrame(windowTitle, windowWidth, windowHeight) {

    init {
        add(BorderLayout.NORTH, AddTraitButton)
        add(TraitListPanel)
        add(BorderLayout.SOUTH, RunGenerationButton)
    }

}
