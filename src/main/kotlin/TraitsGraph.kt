import com.soywiz.korim.bitmap.Bitmap32
import kotlin.math.floor

class TraitsGraph {

    private var amountOfVariants = 0
    private var lastNodes: List<Node>? = null
    private var initNodes: List<Node>? = null

    data class Node(
        val path: String,
        val trait: Bitmap32,
        val nextNodes: MutableList<Node> = mutableListOf()
    ) {
        override fun toString() = path.substringAfterLast('/').substringBefore('.')
    }

    fun addTrait(traitType: String, numberOfVariants: Long) =
        addTrait((1..numberOfVariants).map { path(traitType = traitType, id = it) }).let { this }

    private fun addTrait(paths: List<String>): TraitsGraph {
        val newLayer = paths.map { Node(path = it, trait = createBitmap(loadImage(it))) }

        if (initNodes == null) {
            initNodes = newLayer
            lastNodes = initNodes
            return this
        }

        lastNodes?.forEach { it.nextNodes.addAll(newLayer) }
        lastNodes = newLayer

        return this
    }

    fun generateVariants(printSummary: Boolean = true, generateImages: Boolean = false) {
        initNodes?.forEach { if (generateImages) generateVariants(it) else generateVariants(it, null) }
        if (printSummary) {
            println("Amount of NFT: $amountOfVariants. Be ready to save ${floor(0.062 * amountOfVariants)}MB!")
        }
    }

    private fun generateVariants(node: Node, bitmap: Bitmap32? = Bitmap32(WIDTH, HEIGHT)) {
        if (node.nextNodes.isEmpty()) {
            bitmap?.appendTrait(node.trait)
            if (bitmap != null) renderImage(bitmap)
            amountOfVariants++
            return
        }
        node.nextNodes.forEach { generateVariants(it, bitmap?.clone()?.appendTrait(node.trait)) }
    }

    override fun toString(): String {
        val matrix = mutableListOf<List<String>>()

        initNodes?.map { it.toString() }?.let { matrix.add(it) }

        var node = initNodes?.first()
        while (node != null) {
            matrix.add(node.nextNodes.map { it.toString() })
            node = node.nextNodes.firstOrNull()
        }

        val maxLength = matrix.maxBy { it.size }.size

        val sb = StringBuilder()
        for (j in 0 until maxLength) {
            for (i in matrix.indices) {
                try {
                    sb.append("${matrix[i][j]}\t")
                } catch (e: Exception) {
                    sb.append("\t\t")
                }
            }
            sb.append("\n")
        }

        return sb.toString()
    }

}
