package api.gui.component.panel

import api.gui.component.NftCollectionFrame
import api.gui.traits
import generator.domain.Trait
import generator.domain.TraitName
import generator.domain.TraitVariant
import generator.domain.TraitVariantName
import processor.FileTraitImportProcessor
import processor.domain.FileFormat
import processor.domain.FileName
import processor.domain.Path
import processor.domain.TraitVariantFile
import java.awt.Color
import java.awt.Dimension
import java.io.File
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class TraitListItemPanel : JPanel() {

    init {
        preferredSize = Dimension(400, 100)
        background = Color.BLUE

        add(TraitListItemNameTextField())
        add(ChooseFilesButton())
        add(ProcessTraitButton())

        NftCollectionFrame.revalidate()
    }

    companion object {
        private const val defaultTraitName = "TraitName"
    }

    private var traitName: String = defaultTraitName
    private lateinit var traitVariantFiles: List<File>

    inner class TraitListItemNameTextField : JTextField(defaultTraitName) {
        init {
            addActionListener {
                traitName = text
                isEditable = false
            }
        }
    }

    inner class TraitListItemVariantsLabel(variants: String) : JLabel(variants)

    inner class ChooseFilesButton : JButton("Choose files") {
        init {
            addActionListener {
                val fileChooser = JFileChooser()
                fileChooser.isMultiSelectionEnabled = true

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    traitVariantFiles = fileChooser.selectedFiles.toList()
                    super@TraitListItemPanel.add(
                        TraitListItemVariantsLabel(traitVariantFiles.map { it.nameWithoutExtension }.toString()))
                    NftCollectionFrame.revalidate()
                }
            }
        }
    }

    inner class ProcessTraitButton : JButton("Process") {
        init {
            addActionListener {
                traits.add(
                    FileTraitImportProcessor(
                        TraitName(traitName),
                        traitVariantFiles.map {
                            val file = TraitVariantFile(
                                Path(it.path.substringBeforeLast('/')),
                                FileName(it.nameWithoutExtension),
                                FileFormat.PNG
                            )
                            println("importing $file")
                            file
                        }
                    ).process()
                )
            }
        }
    }

}
