import java.io.File

enum class RPS(private val value: Int) {
    ROCK(1), PAPER(2), SCISSOR(3);

    fun compete(v: RPS) : Int {
        if (this == v) {
            return value + DRAW
        }
        when (this) {
            ROCK -> {
                if (v == PAPER) {
                    return this.value
                } else if (v == SCISSOR) {
                    return this.value + WIN
                }
            }
            PAPER -> {
                if (v == SCISSOR) {
                    return this.value
                } else if (v == ROCK) {
                    return this.value + WIN
                }
            }
            SCISSOR -> {
                if (v == ROCK) {
                    return this.value
                } else if (v == PAPER) {
                    return this.value + WIN
                }
            }
        }
        throw IllegalStateException()
    }



    companion object {
        private const val WIN = 6
        private const val DRAW = 3
        fun convert(letter: Char) : RPS {
            return when (letter) {
                'A', 'X' -> {
                     ROCK
                }
                'B', 'Y' -> {
                     PAPER
                }
                'C', 'Z' -> {
                     SCISSOR
                }

                else -> {
                    throw IllegalStateException()
                }
            }

        }

        fun computeHand(elf: RPS, letter: Char) : RPS {
            if (letter == 'Y') {
                return elf
            }

            when (elf) {
                ROCK -> {
                    when (letter) {
                        'X' -> return SCISSOR
                        'Z' -> return PAPER
                    }
                }
                PAPER ->{
                    when (letter) {
                        'X' -> return ROCK
                        'Z' -> return SCISSOR
                    }
                }
                SCISSOR -> {
                    when (letter) {
                        'X' -> return PAPER
                        'Z' -> return ROCK
                    }
                }
            }
            throw IllegalStateException()
        }

    }
}

fun main(args: Array<String>) {

    val input = File("/home/ben/aoc/input-2.txt")
    var score = 0
    input.forEachLine {
        val elf = RPS.convert(it[0])
        val me = RPS.computeHand(elf, it[2])
        score += me.compete(elf)

    }
    println("score $score")
}


