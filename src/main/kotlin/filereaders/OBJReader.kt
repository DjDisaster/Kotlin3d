package filereaders

import Vector
import matricies.Triangle
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class OBJReader(file: File) {


    var tris = ArrayList<Triangle>()

    init {
        val reader = BufferedReader(FileReader(file))
        val lines = reader.readLines()

        val verts = ArrayList<Vector>()

        for (line in lines) {
            if (line[0] == 'v') {
                val split = line.substring(2).split(" ")
                val x = split[0].toDouble()
                val y = split[1].toDouble()
                val z = split[2].toDouble()
                verts.add(Vector(x,y,z))
            }

            if (line[0] == 'f') {
                val split = line.substring(2).split(" ")
                val x = split[0].toDouble()
                val y = split[1].toDouble()
                val z = split[2].toDouble()

                tris.add(
                    Triangle(
                    verts[x.toInt()-1],
                    verts[y.toInt()-1],
                    verts[z.toInt()-1]
                )
                )

            }



        }


    }
}