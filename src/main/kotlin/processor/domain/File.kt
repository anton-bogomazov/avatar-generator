package processor.domain

import generator.domain.GeneratedImageName

@JvmInline
value class Path(val value: String)

@JvmInline
value class FileName private constructor(private val value: String) {
    companion object {
        // fixme STRING
        fun from(name: String) = FileName(name)
        fun from(imageName: GeneratedImageName) = FileName(imageName.toString())
    }

    override fun toString() = value

}

enum class FileFormat(val value: String) {
    PNG("png");
}
