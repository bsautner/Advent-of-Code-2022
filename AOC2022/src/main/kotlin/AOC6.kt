import java.io.File



fun main(args: Array<String>) {

    val input = File("/home/ben/aoc/input-6.txt").readText()
    getFirstMarker(input, 4)
    getFirstMarker(input, 14)
}

fun getFirstMarker(data: String, window : Int) {
    for (i in 0..data.length) {
        if (i + window <= data.length) {
            val sample = data.subSequence(i, i + window).toSet()
            if (sample.size == window) {
                println("answer is ${i + window}")
                return
            }
        }
    }
}







