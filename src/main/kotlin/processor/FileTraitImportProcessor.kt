package processor

import generator.domain.Trait
import generator.domain.TraitName
import generator.domain.TraitVariant
import generator.domain.TraitVariantBitmap
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
                TraitVariantBitmap(createBitmap(ImageIO.read(file.toFile()).data))
            )
        })

}
