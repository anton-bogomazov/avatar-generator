package api.gui.component.button

import api.gui.traits
import generator.CollectionGenerator
import output.FileOutputPlugin
import processor.domain.Path
import javax.swing.JButton

object RunGenerationButton : JButton() {

    init {
        text = "RUN"
        setSize(50, 50)
        addActionListener {
            val images = CollectionGenerator.generate(traits)
            val output = FileOutputPlugin(Path("/Users/antonbogomazov/Desktop/mushroom_collection/output"))
            images.forEach {
                println("writing file...")
                output.writeGeneratedImage(it)
            }
        }
    }

}
