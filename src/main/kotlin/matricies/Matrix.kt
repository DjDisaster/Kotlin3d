package org.example.matricies

import org.example.Vector

fun multiplyMatrix(v1: Vector, matrix: Array<DoubleArray>): Vector {
    var returnVector = Vector(0.0,0.0,0.0);

    var x = v1.x;
    var y = v1.y;
    var z = v1.z;

    returnVector.x = (((x * matrix[0][0]) + (y * matrix[1][0]) + (z * matrix[2][0]) + (matrix[3][0])));
    returnVector.y = (((x * matrix[0][1]) + (y * matrix[1][1]) + (z * matrix[2][1]) + (matrix[3][1])));
    returnVector.z = (((x * matrix[0][2]) + (y * matrix[1][2]) + (z * matrix[2][2]) + (matrix[3][2])));

    val w = ((x * matrix[0][3]) + (y * matrix[1][3]) + (z * matrix[2][3]) + (matrix[3][3]));

    if (w != 0.0) {
        returnVector.x = (returnVector.x / w);
        returnVector.y = (returnVector.y / w);
        returnVector.z = (returnVector.z / w);
    }

    return returnVector;

}

fun defaultMatrix(): Array<DoubleArray> {
    return arrayOf(
        doubleArrayOf(0.0,0.0,0.0,0.0),
        doubleArrayOf(0.0,0.0,0.0,0.0),
        doubleArrayOf(0.0,0.0,0.0,0.0),
        doubleArrayOf(0.0,0.0,0.0,0.0)
    )
}


interface Matrix {

    fun getMatrix(): Array<Array<Vector>>;

}