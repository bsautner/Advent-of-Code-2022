import java.io.File
import java.util.LinkedList

fun main(args: Array<String>) {

    val input = File("/home/ben/aoc/input-5.txt")


    //Part 1 & 2, convert the top of file to queue
    val data = input.readText()
    val parts = data.split("\n\n")
    val firstState = parts[0].split("\n")
    var queue = initState(firstState)

    input.forEachLine {

        if (it.startsWith("move")) {

            val command = it.split(" ")
            for (i in 1..command[1].toInt()) {
                val p = queue[command[3].toInt() -1].removeLast()
                queue[command[5].toInt()-1].addLast(p)

            }
         }

    }
    for (x in 0..8) {
        print(queue[x].last)
    }

    println()
    //part 2
    queue = initState(firstState)
    input.forEachLine {

        if (it.startsWith("move")) {
            val command = it.split(" ")
            val t = LinkedList<Char>()
            for (i in 1..command[1].toInt()) {
                val p = queue[command[3].toInt() -1].removeLast()
                t.add(p)
            }
            queue[command[5].toInt()-1].addAll(t.reversed())
        }

    }

    //print part 2
    for (x in 0..8) {
        print(queue[x].last)
    }

}

private fun initState( firstState: List<String>): MutableList<LinkedList<Char>> {

    val queue = mutableListOf<LinkedList<Char>>()

    firstState.forEach {
        it.chunked(4).let { chunks ->
            var indx = 0

            chunks.forEach { chunk ->
                queue.add(LinkedList())
                val v = (chunk[1])
                if (v.toString().trim().isNotEmpty()) {
                    if (v.code > 65) {
                        queue[indx].addFirst(v)
                    }
                }
                indx++
            }
        }
    }
    return queue
}