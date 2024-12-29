package matricies

import kotlin.math.cos
import kotlin.math.sin


fun rotationX(degrees: Double): Array<DoubleArray> {
    val radians = degreesToRadians(degrees)
    val matrix = arrayOf(
        doubleArrayOf(1.0, 0.0, 0.0, 0.0),
        doubleArrayOf(0.0, cos(radians), sin(radians), 0.0),
        doubleArrayOf(0.0, -sin(radians), cos(radians), 0.0),
        doubleArrayOf(0.0, 0.0, 0.0, 1.0)
    )
    return matrix
}

fun rotationY(degrees: Double): Array<DoubleArray> {
    val radians = degreesToRadians(degrees)
    val matrix = arrayOf(
        doubleArrayOf(cos(radians), 0.0, -sin(radians), 0.0),
        doubleArrayOf(0.0, 1.0, 0.0, 0.0),
        doubleArrayOf(sin(radians), 0.0, cos(radians), 0.0),
        doubleArrayOf(0.0, 0.0, 0.0, 1.0)
    )

    return matrix
}


fun rotationZ(degrees: Double): Array<DoubleArray> {
    val radians = degreesToRadians(degrees)
    val matrix = arrayOf(
        doubleArrayOf(cos(radians), sin(radians), 0.0, 0.0),
        doubleArrayOf(-sin(radians), cos(radians), 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0, 0.0),
        doubleArrayOf(0.0, 0.0, 0.0, 1.0)
    )

    return matrix
}



fun degreesToRadians(degrees: Double): Double {
    return degrees * Math.PI / 180.0
}