package matricies

class TriangleComparator : Comparator<Triangle> {
    override fun compare(o1: Triangle?, o2: Triangle?): Int {

        if (o1 == null || o2 == null) {
            return -1;
        }

        var matrix1 = o1.getMatrix();
        var matrix2 = o2.getMatrix();
        var z1 = (matrix1[0].z + matrix1[1].z + matrix1[2].z) / 3
        var z2 = (matrix2[0].z + matrix2[1].z + matrix2[2].z) / 3

        if (z1 < z2) {
            return 1;
        }
        return -1;

    }
}