package fusion

object Transformer {

    val e = 1.0

    def horizontalInversion(p: Point): Point = {
        val x = - p.x
        val y = p.y

        Point(x, y)
    }

    def verticalInversion(p: Point): Point = {
        val x = p.x
        val y = - p.y

        Point(x, y)
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

    val at = Array(horizontalInversion _, verticalInversion _)

}
