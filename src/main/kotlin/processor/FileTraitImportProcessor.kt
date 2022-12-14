package processor

import generator.domain.*
import processor.domain.TraitVariantFile
import javax.imageio.ImageIO

class FileTraitImportProcessor(
    traitName: TraitName,
    override val inputData: List<TraitVariantFile>
) : TraitImportProcessor(traitName, inputData) {

    override fun process() =
        Trait(traitName, inputData.map { file ->
            TraitVariant(
                file.variantName(),
                TraitVariantBitmap.from(ImageIO.read(file.toFile()).data))
        })

}
