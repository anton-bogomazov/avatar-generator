package util

fun mapMatrix(height: Int, width: Int, mapper: (Int, Int) -> Unit) {
    for (i in 0 until height) {
        for (j in 0 until width) {
            mapper(i, j)
        }
    }
}
