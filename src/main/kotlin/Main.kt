import matricies.Cube
import java.awt.Color
import java.awt.Toolkit
import java.util.*
import javax.swing.JFrame


lateinit var drawPanel: DrawPanel

fun main() {

    val frame = JFrame()
    val dimensions = Toolkit.getDefaultToolkit().screenSize
    frame.setSize(dimensions.width, dimensions.height)

    frame.background = Color.BLACK

    drawPanel = DrawPanel(
        arrayOf(Cube(-0.5,-0.5,-0.5, 1.0))
    )

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