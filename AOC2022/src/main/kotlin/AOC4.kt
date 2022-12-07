import java.io.File

fun main(args: Array<String>) {

    val input = File("/home/ben/aoc/input-4.txt")

    var part1 = 0
    var part2 = 0
    input.forEachLine { line ->

        val pair = line.split(',')
        val a = pair[0].split("-").map { it.toInt() }
        val b = pair[1].split("-").map { it.toInt() }
        if (((b[0] >= a[0]).and(b[1] <= a[1])) or ((a[0] >= b[0]).and(a[1] <= b[1])) ) {
            part1++
        }
        if(a[0].coerceAtLeast(b[0]) <= a[1].coerceAtMost(b[1])){
            part2++
        }
    }
    println(part1)
    println(part2)



}