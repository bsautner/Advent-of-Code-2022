import java.io.File

class AOC8 {
    private val input = File("/home/ben/aoc/input.txt")

    private val sample = mutableListOf<String>()

    var maxScore = 0


    fun process() {
        input.forEachLine {
            sample.add(it)
        }

        val charArrays = sample.map { it.toCharArray() }
        val matrix = charArrays.toTypedArray()
        var count = 0

        for (y in matrix.indices) {
            for (x in matrix[y].indices)
            {
                if (isVisible(x, y, matrix)) {
                    count++
                }
            }
        }
        println("Part 1 Answer: $count")

        for (y in matrix.indices) {
            for (x in matrix[y].indices)
            {
               scoreTree(x, y, matrix)
            }
        }
        println("Part 2 Answer: $maxScore")

    }

    private fun scoreTree(x: Int, y: Int, matrix: Array<CharArray>)  : Int {
        var up = 0
        var down = 0
        var left = 0
        var right = 0

        if (x == 0 || x == matrix.size -1 || y == 0 || y == matrix.size -1) {
            return 0
        }

        val a = matrix[y][x].digitToInt()
        for (i in x+1 until matrix[y].size) {
            val s = matrix[y][i].digitToInt()
            right++
            if (s >= a) {
                break
            }
        }

        for (i in x-1 downTo 0) {
            val s = matrix[y][i].digitToInt()
            left++
            if (s >= a) {
               break
            }
        }

        for (i in y+1 until matrix.size) {
            val s = matrix[i][x].digitToInt()
            down++
            if (s >= a) {
               break
            }
        }

        for (i in y-1 downTo 0) {
            val s = matrix[i][x].digitToInt()
            up++
            if (s >= a) {
              break
            }
        }

        val score = up * down * left * right

        if (score > maxScore) {
            maxScore = score
        }

        return score
    }

    private fun isVisible(x: Int, y: Int, matrix: Array<CharArray>) : Boolean {

        var up = true
        var down = true
        var left = true
        var right = true


        if (x == 0 || x == matrix.size -1 || y == 0 || y == matrix.size -1) {
            return true
        }

        val a = matrix[y][x].digitToInt()

        for (i in x+1 until matrix[y].size) {
            val s = matrix[y][i].digitToInt()
            if (s >= a) {
                if (right) {
                    right = false
                }

            }
        }

        for (i in x-1 downTo 0) {
            val s = matrix[y][i].digitToInt()
            if (s >= a) {
                if (left) {
                    left = false
                }

            }
        }


        for (i in y+1 until matrix.size) {
            val s = matrix[i][x].digitToInt()
            if (s >= a) {
                if (down) {
                    down = false
                }

            }
        }

        for (i in y-1 downTo 0) {
            val s = matrix[i][x].digitToInt()
            if (s >= a) {
                if (up) {
                    up = false
                }

            }
        }


        return up or down or left or right



    }



}