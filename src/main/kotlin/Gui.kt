import java.io.File
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JOptionPane
import kotlin.system.exitProcess

var frame = JFrame()

fun getFile(path: String): File {
    val chooser = JFileChooser()
    chooser.currentDirectory = File(path)

    val result = chooser.showOpenDialog(frame.parent)
    if (result == JFileChooser.APPROVE_OPTION) {
        return chooser.selectedFile
    } else {
        println("invalid path")
        exitProcess(DEFAULT_BUFFER_SIZE)
    }
}

fun choose(): Int {
    return JOptionPane.showOptionDialog(
        frame.parent,
        "choose the background",
        null,
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE, null, arrayOf("wight on black", "black on wight"), 0
    )
}

fun close() {
}