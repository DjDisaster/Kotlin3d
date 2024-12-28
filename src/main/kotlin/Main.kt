package org.example

import org.example.matricies.Cube
import java.awt.Color
import java.awt.Toolkit
import java.util.*
import javax.swing.JFrame
import javax.swing.JLabel


lateinit var drawPanel: DrawPanel;

fun main() {

    val frame = JFrame();
    val dimensions = Toolkit.getDefaultToolkit().screenSize;
    frame.setSize(dimensions.width, dimensions.height);

    frame.background = Color.BLACK;

    drawPanel = DrawPanel(
        arrayOf(Cube(0.0,0.0,0.0, 1.0))
    )

    //drawPanel.background = Color.BLACK;
    frame.add(drawPanel);
    frame.isUndecorated = true;
    frame.isVisible = true;

    var label = JLabel();
    label.text = "hello world";
    label.background = Color.YELLOW;
    drawPanel.add(label);
    drawPanel.background = Color.BLACK;

    drawPanel.repaint();


    var timer = Timer();
    timer.scheduleAtFixedRate(
        object : TimerTask() {

            override fun run() {
                drawPanel.repaint();
            }

        },0, 10
    )




}