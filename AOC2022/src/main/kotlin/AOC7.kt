import java.io.File


class AOC7 {
    private val input = File("/home/ben/aoc/input-7.txt")
    private val list = mutableListOf<String>()

    private var PWD = Node("root", null) //Present Working Directory

    var spaceNeeded = 0L
    var winner = 70000000L

    fun process() {
        input.forEachLine { list.add(it) }
        PWD.nodes["/"] = Node("/", PWD)
        buildNodeTreeFromCommandList(list.listIterator(), PWD)

        val answer =  getSmallNodesSum(PWD)
        println("Part 1 $answer")

        val rootSize = getNodeSize(PWD)
        spaceNeeded =  MAX_HD - rootSize
        spaceNeeded = UPDATE_SIZE - spaceNeeded
        smallestDeletableDir(PWD)
        println("Part 2 $winner")
    }

    private fun smallestDeletableDir(node: Node)  {

        if (getNodeSize(node) in (spaceNeeded + 1) until winner) {
            winner = getNodeSize(node)

        }
        node.nodes.values.forEach {
            smallestDeletableDir(it)
        }

    }

    private fun getSmallNodesSum(node: Node) : Long {
        var sum = 0L

        node.nodes.values.forEach {
            if (getNodeSize(it) <= 100000) {
                sum += getNodeSize(it)
            }
            sum += getSmallNodesSum(it)
        }

        return sum
    }

    private fun getNodeSize(node: Node) : Long {

        var size = 0L
        node.items.forEach {
            size += it.size
        }
        node.nodes.values.forEach {
            size += getNodeSize(it)
        }
        return size

    }

    private fun buildNodeTreeFromCommandList(iterator: MutableIterator<String>, pwd: Node?) {
        if (iterator.hasNext()) {
            val line = iterator.next()
            val parts = line.split(" ")

            if (isNumeric(parts[0])) {
                val item = Item(parts[1], parts[0].toLong())
                pwd?.items?.add(item)
                buildNodeTreeFromCommandList(iterator, pwd)
            }
            else {
                when (parts[0]) {
                    "dir" -> {
                        pwd?.nodes?.set(parts[1], Node(parts[1], pwd))
                        buildNodeTreeFromCommandList(iterator, pwd)
                    }
                    "$" -> {
                        if (parts[1] == "cd") {
                            if (parts[2] == "..") {
                                buildNodeTreeFromCommandList(iterator, pwd?.parent)
                            } else {
                                val node = pwd?.nodes?.get(parts[2])
                                buildNodeTreeFromCommandList(iterator, node)
                            }

                            //    pwd = pwd.nodes[parts[2]]!!
                        } else if (parts[1] == "ls") {
                            buildNodeTreeFromCommandList(iterator, pwd)
                        }
                    }

                }
            }



        }
    }

    companion object {

        private const val MAX_HD = 70000000
        private const val UPDATE_SIZE = 30000000

        @JvmStatic
        fun main(args: Array<String>) {
            AOC7().process()
        }
        fun isNumeric(toCheck: String): Boolean {
            return toCheck.toDoubleOrNull() != null
        }
    }
}

data class Node(private val name: String, val parent: Node?) {
    val nodes = mutableMapOf<String, Node>()
    val items = mutableListOf<Item>()
}

data class Item(private val name: String, val size: Long)
