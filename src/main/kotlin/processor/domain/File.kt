package processor.domain

@JvmInline
value class Path(val value: String)

@JvmInline
value class FileName(val value: String)

enum class FileFormat(val value: String) {
    PNG("png");
}
