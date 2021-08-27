import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageDetails(path: File, w: Boolean) {

    val image: BufferedImage
    val lines: List<MutableList<Int>>

    init {
        image = convertImage(ImageIO.read(path))

        lines = List(image.height) {
            mutableListOf()
        }

        for (i in lines.indices) {

            var gap = 0

            for (j in 0 until image.width) {
                if((image.getRGB(j, i) == -1) == w) {
                    gap++
                } else {
                    lines[i].add(gap)
                    gap = 0
                }
            }
            lines[i].add(gap)
        }
    }

    private fun convertImage(i: BufferedImage): BufferedImage {
        val image = BufferedImage(i.width, i.height, BufferedImage.TYPE_BYTE_BINARY)
        image.graphics.drawImage(i, 0, 0, null)
        return image
    }
}