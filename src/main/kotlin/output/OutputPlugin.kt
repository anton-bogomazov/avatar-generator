package output

import generator.domain.GeneratedImage

interface OutputPlugin {

    fun writeGeneratedImage(image: GeneratedImage)

}
