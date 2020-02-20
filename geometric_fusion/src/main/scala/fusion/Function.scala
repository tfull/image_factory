package fusion

object Function {

    def clip(v: Double): Double = {
        if (v < 0.0) {
            0.0
        } else if (v > 1.0) {
            1.0
        } else {
            v
        }
    }

    // (x, y) => (x, y)
    /*
    def grid(p: Point): Point = {
        val k = 10
        val w = k.toDouble

        val p_low = (0 until k).map( i =>  -e + 2.0 * e / w * i ).filter()
        p_low
    }
    */

    def diagonalLines(p: Point): Double = {
        val big_u = 100.0 * Constant.unit_value

        clip(((10 * (p.x + p.y + big_u)).toInt % 2).toDouble)
    }

    def circle(p: Point): Double = {
        val u = Constant.unit_value

        clip((p.x * p.x + p.y * p.y - u).abs / u)
    }

    val at: Array[Point => Double] = Array(diagonalLines _, circle _)
}
