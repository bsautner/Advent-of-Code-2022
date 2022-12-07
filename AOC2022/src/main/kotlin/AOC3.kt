import java.io.File

    fun main(args: Array<String>) {

        val input = File("/home/ben/aoc/input-3.txt")

        var sum = 0

        //Part 1
        input.forEachLine {
            val a = deDup(it.subSequence(0, it.length / 2))
            val b = deDup(it.subSequence(it.length / 2, it.length))
            a.forEach {c ->
                if (b.contains(c)) {
                   sum += getPriority(c)
                }
            }


        }
        println("part 1 $sum")

        //part 2
        val list = mutableListOf<String>()
        var badgeSum = 0
        input.forEachLine {
            list.add(it)
        }
        var idx = -1
        while (idx++ < list.size -1) {
            val a = list[idx]
            val b = list[++idx]
            val c = list[++idx]
            val badge = getBadge(a, b, c)
            val priority = getPriority(badge)
            badgeSum += priority
        }
        println(badgeSum)

    }

    fun getBadge(vararg s: String) : Char {
         s[0].toCharArray().forEach {
             if (s[1].contains(it) && s[2].contains(it)) {
                 println(it)
                 return it
             }
         }
        throw IllegalStateException()
    }

    fun getPriority(c : Char) : Int {

        return if (c.code > 96) {
            c.code - 96
        } else {
            c.code - 65 + 27
        }
    }

    fun deDup(s : CharSequence) : String {

        val sb = StringBuilder()
        s.forEach {
            if (! sb.contains(it)) {
                sb.append(it)
            }
        }
        return sb.toString()

    }

