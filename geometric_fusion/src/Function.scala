package src

object Function {
    val e = 1.0

    // (x, y) => (x, y)
    /*
    def grid(p: Point): Point = {
        val k = 10
        val w = k.toDouble

        val p_low = (0 until k).map( i =>  -e + 2.0 * e / w * i ).filter()
        p_low
    }
    */


    // (x, y) => z
    // -1 <= x <= -1
    // -1 <= y <= -1
    // 0 <= z <= 1

    def f1(p: Point): Double = {
        ((10 * (p.x + p.y).abs).toInt % 2).toDouble
    }

    def f2(p: Point): Double = {
        (p.x * p.x + p.y * p.y - e).abs
    }

    val array: Array[Point => Double] = Array(f1 _, f2 _)

    // color => color
    // 0 <= color <= 1

    def c1(x: Double): Double = {
        1 - x
    }

    def c2(x: Double): Double = {
        x / 2 + 0.5
    }
}