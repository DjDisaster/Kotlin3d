
import filereaders.OBJReader
import java.awt.Color
import java.awt.Toolkit
import java.io.File
import java.util.*
import javax.swing.JFrame


lateinit var drawPanel: DrawPanel

fun main() {

    val frame = JFrame()
    val dimensions = Toolkit.getDefaultToolkit().screenSize
    frame.setSize(dimensions.width, dimensions.height)

    frame.background = Color.BLACK

    val obj = {}.javaClass.getResource("teapot.obj")
    if (obj == null) {
        println("OBJ was null.")
        return
    }
    val filePath = obj.path
    val file = File(filePath)
    val reader = OBJReader(file)


    drawPanel = DrawPanel(
        reader.tris.toTypedArray()
        //arrayOf(Triangle(-0.5,-0.5,-0.5, 1.0))
    )

    drawPanel.setSize(dimensions.width, dimensions.height)

    frame.add(drawPanel)
    frame.isUndecorated = true
    frame.isVisible = true


    drawPanel.background = Color.BLACK

    drawPanel.repaint()


    val timer = Timer()
    timer.scheduleAtFixedRate(
        object : TimerTask() {
            override fun run() {
                drawPanel.repaint()
            }
        },0, 10
    )




}