package fusion

object Transformer {

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

    def grid(p: Point): Point = {
        val k = 10
        val u = Constant.unit_value
        val big_unit = 100.0

        val inside_x = (p.x + big_unit * u) % (2 * u / k.toDouble)
        val inside_y = (p.y + big_unit * u) % (2 * u / k.toDouble)


        Point(- u + inside_x * k.toDouble, - u + inside_y * k.toDouble)
    }

    val at = Array(horizontalInversion _, verticalInversion _, grid _)

}
