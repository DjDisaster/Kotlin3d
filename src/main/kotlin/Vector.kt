package org.example

class Vector(var x: Double, var y: Double, var z: Double) {

    fun copy(): Vector {
        return Vector(x,y,z)
    }

    fun multiply(amm: Double) {
        x *= amm
        y *= amm
        z *= amm
    }

}