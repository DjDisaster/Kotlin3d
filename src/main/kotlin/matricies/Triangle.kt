package matricies

import Vector

class Triangle(private var v1: Vector, private var v2: Vector, private var v3: Vector) : Matrix {

    private fun getVector(): Array<Vector> {
        return arrayOf(
            v1,
            v2,
            v3
        )
    }

    override fun getMatrix(): Array<Array<Vector>> {
        return arrayOf(
            getVector()
        )
    }
}