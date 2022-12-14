package output

import generator.domain.GeneratedImage
import output.domain.OutputFile
import processor.domain.FileName
import processor.domain.Path
import javax.imageio.ImageIO

class FileOutputPlugin(
    private val path: Path
) : OutputPlugin {

    // todo create path if not existed
    override fun writeGeneratedImage(image: GeneratedImage) {
        val file = OutputFile(path, FileName.from(image.name))
        ImageIO.write(image.render(), file.format.value, file.toFile())
    }

}
