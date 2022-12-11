package api.gui.component.panel

import java.awt.Color
import javax.swing.JPanel

object TraitListPanel : JPanel() {

    init {
        background = Color.GRAY
    }

    fun addTraitItem(trait: TraitListItemPanel) {
        add(trait)
    }

}
