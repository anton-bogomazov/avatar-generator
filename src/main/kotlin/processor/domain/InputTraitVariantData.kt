package processor.domain

import generator.domain.TraitVariantName
import java.io.File

interface InputTraitVariantData

class TraitVariantFile(
    private val path: Path,
    private val fileName: FileName,
    private val format: FileFormat = FileFormat.PNG
) : InputTraitVariantData {

    fun toFile(): File = File("${path.value}/${fileName.value}.${format.value}")

    fun variantName() = TraitVariantName(fileName.value)

}
