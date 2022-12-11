package api.gui.abstraction

import java.awt.BorderLayout
import javax.swing.JFrame

abstract class BaseFrame(title: String, width: Int, height: Int) : JFrame() {

    init {
        this.title = title
        setSize(width, height)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
        layout = BorderLayout()
    }

}
