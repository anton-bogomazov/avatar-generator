package processor

import generator.domain.Trait
import generator.domain.TraitName
import processor.domain.InputTraitVariantData

abstract class TraitImportProcessor(
    val traitName: TraitName,
    open val inputData: List<InputTraitVariantData>
) {

    abstract fun process(): Trait

}
