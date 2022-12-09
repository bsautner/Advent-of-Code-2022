import java.io.File

class AOC8 {

    var maxScore = 0


    fun process() {
        val input = File("/home/ben/aoc/input-8.txt")
        val sample = input.useLines { it.toList() }
        val matrix = sample.map { it.toCharArray() }.toTypedArray()
        var count = 0

        matrix.mapIndexed { y, row ->
            row.mapIndexed { x, _ ->
                if (isVisible(x, y, matrix)) {
                    count++
                }
                scoreTree(x, y, matrix)
            }
        }

        println("Part 1 Answer: $count")
        println("Part 2 Answer: $maxScore")

    }

    private fun scoreTree(x: Int, y: Int, matrix: Array<CharArray>)  : Int {
        if (x == 0 || x == matrix.size -1 || y == 0 || y == matrix.size -1) {
            return 0
        }

        val a = matrix[y][x].digitToInt()

        val right = matrix[y].takeWhile { it.digitToInt() < a }.count()
        val left = matrix[y].reversed().takeWhile { it.digitToInt() < a }.count()
        val down = matrix.takeWhile { it[x].digitToInt() < a }.count()
        val up = matrix.reversed().takeWhile { it[x].digitToInt() < a }.count()

        val score = up * down * left * right

        if (score > maxScore) {
            maxScore = score
        }

        return score
    }

    private fun isVisible(x: Int, y: Int, matrix: Array<CharArray>) : Boolean {
        if (x == 0 || x == matrix.size -1 || y == 0 || y == matrix.size -1) {
            return true
        }

        val a = matrix[y][x].digitToInt()

        val right = matrix[y].takeWhile { it.digitToInt() < a }.count()
        val left = matrix[y].reversed().takeWhile { it.digitToInt() < a }.count()
        val down = matrix.takeWhile { it[x].digitToInt() < a }.count()
        val up = matrix.reversed().takeWhile { it[x].digitToInt() < a }.count()

        return right == 0 || left == 0 || down == 0 || up == 0
    }



}