package com.yp.day9

import java.io.File
import kotlin.math.abs

class AOC9 {


    fun process() {
        val input =  File("/home/ben/aoc/input-9.txt")

        val instructions =  input.useLines { it.toList() }

        val p1 = (0..1).map { Pair(0, 0) }.toMutableList()
        val p2 = (0..9).map { Pair(0, 0) }.toMutableList()

        println("Part1: ${scan(p1, instructions)}")
        println("Part2: ${scan(p2, instructions)}")


    }

    private fun scan(rope: MutableList<Pair<Int, Int>>, input: List<String>): Int {
        val visitedPositions = mutableSetOf<Pair<Int, Int>>()
        visitedPositions.add(rope.last())
        input.forEach { instruction ->
            val cmd = instruction.split(" ")
            (1..cmd[1].toInt()).forEach { _ ->
                rope[0] = processStep(cmd[0], rope[0])
                (0 until rope.size - 1).forEachIndexed { index, _ ->
                    val newPositions =
                        tail(rope[index], rope[index + 1])

                    rope[index + 1] = newPositions
                }
                visitedPositions.add(rope.last())
            }
        }
        return visitedPositions.size
    }

    private fun tail(
        headPosition: Pair<Int, Int>,
        tailPosition: Pair<Int, Int>
    ): Pair<Int, Int> {
        return when {
            abs((headPosition.first - tailPosition.first)) > 1 || abs((headPosition.second - tailPosition.second)) > 1 -> {
                when {
                    headPosition.first == tailPosition.first -> {
                        Pair(
                            tailPosition.first,
                            if (headPosition.second > tailPosition.second) tailPosition.second + 1 else tailPosition.second - 1
                        )
                    }
                    headPosition.second == tailPosition.second -> {
                        Pair(
                            if (headPosition.first > tailPosition.first) tailPosition.first + 1 else tailPosition.first - 1,
                            tailPosition.second
                        )
                    }
                    else -> {
                        val xDiff = (headPosition.first - tailPosition.first)
                        val yDiff = (headPosition.second - tailPosition.second)
                        val changeX = abs(xDiff) > 1 || (abs(xDiff) > 0 && abs(yDiff) > 1)
                        val changeY = abs(yDiff) > 1 || (abs(yDiff) > 0 && abs(xDiff) > 1)
                        Pair(
                            if (changeX) tailPosition.first + (if (xDiff < 0) -1 else 1) else tailPosition.first,
                            if (changeY) tailPosition.second + (if (yDiff < 0) -1 else 1) else tailPosition.second,
                        )
                    }
                }
            }
            else -> tailPosition
        }
    }


    private fun processStep(step: String, headPosition: Pair<Int, Int>): Pair<Int, Int> {
        val (x, y) = headPosition
        return when (step) {
            "U" -> headPosition.copy(second = y - 1)
            "D" -> headPosition.copy(second = y + 1)
            "L" -> headPosition.copy(first = x - 1)
            "R" -> headPosition.copy(first = x + 1)
            else -> { throw IllegalStateException() }
        }
    }
}
