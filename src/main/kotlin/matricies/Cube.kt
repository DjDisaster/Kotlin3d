package org.example.matricies

import org.example.Vector



class Cube(var xOffset: Double, var yOffset: Double, var zOffset: Double, var scale: Double) : Matrix {

    private val matrix = arrayOf(
        arrayOf(
            Vector(0.0, 0.0, 0.0),
            Vector(0.0, 1.0, 0.0),
            Vector(1.0, 1.0, 0.0),
        ),
        arrayOf(
            Vector(0.0, 0.0, 0.0),
            Vector(1.0, 1.0, 0.0),
            Vector(1.0, 0.0, 0.0),
        ),
        arrayOf(
            Vector(1.0, 0.0, 0.0),
            Vector(1.0, 1.0, 0.0),
            Vector(1.0, 1.0, 1.0),
        ),
        arrayOf(
            Vector(1.0, 0.0, 0.0),
            Vector(1.0, 1.0, 1.0),
            Vector(1.0, 0.0, 1.0),
        ),
        arrayOf(
            Vector(1.0, 0.0, 1.0),
            Vector(1.0, 1.0, 1.0),
            Vector(0.0, 1.0, 1.0),
        ),
        arrayOf(
            Vector(1.0, 0.0, 1.0),
            Vector(0.0, 1.0, 1.0),
            Vector(0.0, 0.0, 1.0),
        ),
        arrayOf(
            Vector(0.0, 0.0, 1.0),
            Vector(0.0, 1.0, 1.0),
            Vector(0.0, 1.0, 0.0),
        ),
        arrayOf(
            Vector(0.0, 0.0, 1.0),
            Vector(0.0, 1.0, 0.0),
            Vector(0.0, 0.0, 0.0),
        ),
        arrayOf(
            Vector(0.0, 1.0, 0.0),
            Vector(0.0, 1.0, 1.0),
            Vector(1.0, 1.0, 1.0),
        ),
        arrayOf(
            Vector(1.0, 0.0, 1.0),
            Vector(0.0, 0.0, 0.0),
            Vector(1.0, 0.0, 0.0),
        ),
        arrayOf(
            Vector(1.0, 0.0, 1.0),
            Vector(0.0, 0.0, 1.0),
            Vector(0.0, 0.0, 0.0),
        ),
        arrayOf(
            Vector(0.0, 1.0, 0.0),
            Vector(1.0, 1.0, 1.0),
            Vector(1.0, 1.0, 0.0),
        ),
    )


    init {
        for (v in matrix) {
            for (v2 in v) {
                v2.x *= scale;
                v2.y *= scale;
                v2.z *= scale;

                v2.x += xOffset;
                v2.y += yOffset;
                v2.z += zOffset;

            }
        }
    }

    override fun getMatrix(): Array<Array<Vector>> {
        return matrix;
    }


}