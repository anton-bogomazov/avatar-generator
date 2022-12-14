package processor.domain

import generator.domain.TraitVariantName
import java.io.File

interface InputTraitVariantData

data class TraitVariantFile(
    private val path: Path,
    private val fileName: FileName,
    private val format: FileFormat = FileFormat.PNG
) : InputTraitVariantData {

    fun toFile(): File = File("${path.value}/${fileName}.${format.value}")

    fun variantName() = TraitVariantName(fileName.toString())

}
