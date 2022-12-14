package output.domain

import processor.domain.FileFormat
import processor.domain.FileName
import processor.domain.Path
import java.io.File

class OutputFile(
    private val path: Path,
    private val fileName: FileName,
    val format: FileFormat = FileFormat.PNG
) {

    fun toFile(): File = File("${path.value}/${fileName}.${format.value}")

}
