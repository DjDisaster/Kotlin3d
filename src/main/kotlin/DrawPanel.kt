
import matricies.*
import java.awt.Color
import java.awt.Graphics
import java.awt.Polygon
import javax.swing.JPanel
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.math.tan


class DrawPanel(private var matricies: Array<Matrix>) : JPanel() {

    private var updateNumber = 0.0

    @Override
    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        var triangles = 0
        var culled = 0
        updateNumber += 1

        val nearPlane = 0.1
        val farPlane = 1000.0
        val fov = 90.0
        val aspectRatio = 1.0
        val fovRad = 1.0 / tan(fov * (0.5 / 180 * Math.PI))

        val projectionMatrix = defaultMatrix()
        projectionMatrix[0][0] = aspectRatio * fovRad
        projectionMatrix[1][1] = fovRad
        projectionMatrix[2][2] = farPlane / (farPlane - nearPlane)
        projectionMatrix[3][2] = (-farPlane * nearPlane) / (farPlane - nearPlane)
        projectionMatrix[2][3] = 1.0
        projectionMatrix[3][3] = 0.0

        val theta = updateNumber

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

        val cameraPosition = Vector(0.0,0.0,0.0)

        for (v in matricies) {

            val matrix = v.getMatrix()

            for (v2 in matrix) {
                val tri = arrayOf(v2[0].copy(), v2[1].copy(), v2[2].copy())

                for (index in tri.indices) {
                    tri[index] = multiplyMatrix(tri[index], rotationX(theta))
                    tri[index] = multiplyMatrix(tri[index], rotationZ(theta))
                    tri[index] = multiplyMatrix(tri[index], rotationY(theta))
                    tri[index].z += 3
                }

                val line1 = Vector((tri[1].x - tri[0].x), (tri[1].y - tri[0].y), (tri[1].z - tri[0].z))
                val line2 = Vector((tri[2].x - tri[0].x), (tri[2].y - tri[0].y), (tri[2].z - tri[0].z))

                val normalX = ((line1.y) * (line2.z)) - ((line1.z) * (line2.y))
                val normalZ = ((line1.x) * (line2.y)) - ((line1.y) * (line2.x))
                val normalY = ((line1.z) * (line2.x)) - ((line1.x) * (line2.z))


                val dot = ((normalX * (tri[1].x - (cameraPosition.x)) + (normalY * (tri[1].y - (cameraPosition.y))) + (normalZ * (tri[1].z - (cameraPosition.z)))))

                if (dot > 0) {
                    culled++
                    continue
                }

                val points = tri.clone()
                for (i in tri.indices) {
                    val v3 = tri[i]
                    points[i] = multiplyMatrix(v3, projectionMatrix)
                    points[i].multiply(500.0)


                    points[i].x += 600
                    points[i].y += 400

                }
                drawTriangle(g, points[0], points[1], points[2])
                triangles++
            }

        }
        println("Triangles: $triangles Culled: $culled")
    }



    private fun drawTriangle(g: Graphics?, v1: Vector, v2: Vector, v3: Vector) {

        if (g == null) {
            return
        }

        val triangle = Polygon()
        triangle.addPoint(floor(v2.x).toInt(), floor(v2.y).toInt())
        triangle.addPoint(floor(v3.x).toInt(), floor(v3.y).toInt())
        triangle.addPoint(floor(v1.x).toInt(), floor(v1.y).toInt())

        g.color = Color(255,0,0)
        g.fillPolygon(triangle)

        g.color = Color(255,255,255)
        g.drawLine(floor(v1.x).toInt(), floor(v1.y).toInt(), floor(v2.x).toInt(), floor(v2.y).toInt())
        g.drawLine(floor(v2.x).toInt(), floor(v2.y).toInt(), floor(v3.x).toInt(), floor(v3.y).toInt())
        g.drawLine(floor(v3.x).toInt(), floor(v3.y).toInt(), floor(v1.x).toInt(), floor(v1.y).toInt())
    }



}
