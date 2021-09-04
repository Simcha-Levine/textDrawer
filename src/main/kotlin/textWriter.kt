import java.io.File

val dictionary = Dictionary("alotoftext")

fun writeText(path: File, imageDetails: ImageDetails): File {

    val text = File("${path.path}/mess.txt")

    text.printWriter().use { out ->

        for (line in imageDetails.lines) {
            var t = ""
            for (j in line) {
                t += dictionary.getLine(j)
                t += " "
            }
            out.println(t)
        }
    }
    return text
}