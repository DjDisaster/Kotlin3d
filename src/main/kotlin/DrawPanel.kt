package org.example

import org.example.matricies.Matrix
import org.example.matricies.defaultMatrix
import org.example.matricies.multiplyMatrix
import java.awt.Color
import java.awt.Graphics
import java.awt.Polygon
import javax.swing.JPanel
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan


class DrawPanel(var matricies: Array<Matrix>) : JPanel() {

    var updateNumber = 0.0;

    @Override
    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        println("hi");

        updateNumber += 0.02;

        var nearPlane = 0.1;
        var farPlane = 1000.0;
        var fov = 90.0;
        var aspectRatio = 1.0;
        var fovRad = 1.0 / tan(fov * (0.5 / 180 * Math.PI));

        var projectionMatrix = defaultMatrix();
        projectionMatrix[0][0] = aspectRatio * fovRad;
        projectionMatrix[1][1] = fovRad;
        projectionMatrix[2][2] = farPlane / (farPlane - nearPlane);
        projectionMatrix[3][2] = (-farPlane * nearPlane) / (farPlane - nearPlane);
        projectionMatrix[2][3] = 1.0;
        projectionMatrix[3][3] = 0.0;

        var theta = updateNumber;

        val rotation1 = defaultMatrix()
        rotation1[0][0] = cos(theta)
        rotation1[0][1] = -1 * sin(theta)
        rotation1[1][0] = sin(theta)
        rotation1[1][1] = cos(theta)
        rotation1[2][2] = 1.0
        rotation1[3][3] = 1.0

        val rotation2 = defaultMatrix()
        rotation2[0][0] = 1.0
        rotation2[1][1] = cos(theta)
        rotation2[1][2] = -1 * sin(theta)
        rotation2[2][1] = sin(theta)
        rotation2[2][2] = cos(theta)
        rotation2[3][3] = 1.0

        var cameraPosition = Vector(0.0,0.0,0.0);

        for (v in matricies) {
            var matrix = v.getMatrix();

            for (v2 in matrix) {
                var tri = arrayOf(v2[0].copy(), v2[1].copy(), v2[2].copy());

                for (index in tri.indices) {
                    tri[index] = multiplyMatrix(tri[index], rotation1);
                    tri[index] = multiplyMatrix(tri[index], rotation2);
                    var v3 = tri[index];
                    v3.z += 3;
                }

                var line1 = Vector((tri[1].x - tri[0].x), (tri[1].y - tri[0].y), (tri[1].z - tri[0].z));
                var line2 = Vector((tri[2].x - tri[0].x), (tri[2].y - tri[0].y), (tri[2].z - tri[0].z));

                var normalX = ((line1.y) * (line2.z)) - ((line1.z) * (line2.y));
                var normalZ = ((line1.x) * (line2.y)) - ((line1.y) * (line2.x));
                var normalY = ((line1.z) * (line2.x)) - ((line1.x) * (line2.z));

                val points = tri.clone();
                for (i in tri.indices) {
                    var v3 = tri[i];
                    points[i] = multiplyMatrix(v3, projectionMatrix);
                    points[i].x *= 500;
                    points[i].y *= 500;
                    points[i].z *= 500;

                    points[i].x += 600;
                    points[i].y += 400;

                }
                drawTriangle(g, points[0], points[1], points[2]);
            }

        }
    }

    fun drawTriangle(g: Graphics?, v1: Vector, v2: Vector, v3: Vector) {
        var triangle = Polygon();
        triangle.addPoint(Math.floor(v2.x).toInt(), Math.floor(v2.y).toInt())
        triangle.addPoint(Math.floor(v3.x).toInt(), Math.floor(v3.y).toInt())
        triangle.addPoint(Math.floor(v1.x).toInt(), Math.floor(v1.y).toInt())

        var color = Color(255,0,0);


        if (g != null) {
            g.color = color;
            g.fillPolygon(triangle)
        };

        println("V1: X: " + v1.x + " Y: " + v1.y);
        println("V2: X: " + v2.x + " Y: " + v2.y);
        println("V3: X: " + v3.x + " Y: " + v3
            .y);

    }



}
