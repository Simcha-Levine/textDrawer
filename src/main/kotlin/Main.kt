import java.io.File
import javax.imageio.ImageIO
import kotlin.system.exitProcess

fun mkRis(): File {
    val path = "ris"

    val dir = File(path)
    if (!dir.isDirectory) {
        dir.mkdir()
    }

    return dir
}

fun main() {

    val op = System.getProperty("os.name")

    val dir = mkRis()

    val imagePath: File

    val last = File("last.txt")
    if (last.isFile && last.readText().isNotEmpty()) {
        imagePath = getFile(last.readText())
    } else {
        imagePath = getFile("/home")
        last.writeText(imagePath.path)
    }

    val w: Boolean = choose() == 0

    val imageDetails = ImageDetails(imagePath, w)

    val message = nameDir(dir.path, imagePath.name)
    message.mkdir()

    ImageIO.write(imageDetails.image, "png", File("${message.path}/origen.png"))

    val text = writeText(message, imageDetails)

    println("finished successfully")

    if(!op.equals("Windows 10")) {

        Runtime.getRuntime().exec("google-chrome-stable ${text.path}")

    }
}

fun nameDir(path: String, name: String): File {


    var i = 0
    while (File("${path}/${removeWordType(name)}_$i").isDirectory) {
        i++
    }
    return File("${path}/${removeWordType(name)}_$i")
}

fun getImagePath(dir: File): String {

    println("enter image path:")
    val input = readLine()
    var imagePath = ""
    if (input != "" && input != null && File(input).isFile) {
        imagePath = input
    } else {
        println("invalid path")
        exitProcess(DEFAULT_BUFFER_SIZE)
    }
    return imagePath
}

fun removeWord(word: String, string: String): String {

    var end = string

    if (string.contains(word)) {

        end = string.replace("$word ", "")
        end = end.replace(" $word", "")
        end = end.replace(word, "")
    }
    return end
}

fun removeWordType(string: String): String {

    var endName = removeWord(".png", string)
    endName = removeWord(".jpg", endName)
    endName = removeWord(".gif", endName)

    return endName
}