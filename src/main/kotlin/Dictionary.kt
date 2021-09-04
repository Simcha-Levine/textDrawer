import java.io.File
import kotlin.math.max

class Dictionary(path: String) {

    val words: List<String>
    val largest: String
    val smallest: String

    init {
        words = mkDictionary(path)
        largest = finedLargest(words)
        smallest = finedSmallest(largest, words)
    }

    fun getLine(length: Int): String {
        var line = ""

        var left = length + 1
        while (left > 1) {

            var index = (words.indices).random()
            while (words[index].length > left) {
                index = nextIndex(words.size, index)
            }
            line += words[index]
            left -= words[index].length
        }
        if(line.isNotEmpty()) {

            line = line.removeRange((line.length - 1) until line.length)
            if (left == 1) {
                line += randomChar()
            }
        }
        return line
    }

}

private fun mkDictionary(path: String): List<String> {
    val text: String = File(path).readText()
    var words = mutableListOf<String>()

    var word = ""
    for (i in text) {

        if (i == ' ' || i == '\n' || i == '\r') {
            words.add(word)
            word = ""
        } else {
            word += i
        }
    }
    words = words.filter { it != "" && it != " " } as MutableList<String>
    words = words.map { "$it " } as MutableList<String>
    return words
}

private fun finedLargest(words: List<String>): String {
    var w = ""
    for (i in words) {
        if (i.length > w.length) {
            w = i
        }
    }
    return w
}

private fun finedSmallest(max: String, words: List<String>): String {
    var w = max
    for (i in words) {
        if (i.length < w.length) {
            w = i
        }
    }
    return w
}

private fun nextIndex(size: Int, index: Int) = (index + 1) % (size - 1)

private fun randomChar(): Char {

    when ((0..5).random()) {
        0 -> {
            return '!'
        }
        1 -> {
            return '?'
        }
        2 -> {
            return '$'
        }
        3 -> {
            return ':'
        }
        else -> {
            return ';'
        }
    }
}