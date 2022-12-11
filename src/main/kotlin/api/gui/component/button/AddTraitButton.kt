package api.gui.component.button

import api.gui.component.NftCollectionFrame
import api.gui.component.panel.TraitListItemPanel
import api.gui.component.panel.TraitListPanel
import javax.swing.JButton

object AddTraitButton : JButton() {

    init {
        text = "+"
        setSize(50, 50)
        addActionListener {
            TraitListPanel.addTraitItem(TraitListItemPanel())
            NftCollectionFrame.revalidate()
        }
    }

}
