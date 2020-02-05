object Main {
    def main(args: Array[String]) {
        val width = 1024
        val height = 1024

        println("P3")
        println("%d %d".format(width, height))
        println(255)

        val r_func = (p: Point) => Function.c1(Function.f1(p))
        val g_func = (p: Point) => Function.f2(p)
        val b_func = (p: Point) => Function.c2(Function.f2(p))

        for (i <- 0 until height) {
            for (j <- 0 until width) {
                val x = -1.0 + (2.0 / width.toDouble) * j + (1.0 / width.toDouble)
                val y = 1.0 - (2.0 / height.toDouble) * i - (1.0 / height.toDouble)
                val p = Point(x, y)
                val r = (r_func(p) * 255 + 0.5).toInt
                val g = (g_func(p) * 255 + 0.5).toInt
                val b = (b_func(p) * 255 + 0.5).toInt
                println("%d %d %d".format(r, g, b))
            }
        }
    }
}

object Function {
    // (x, y) => z
    // -1 <= x <= -1
    // -1 <= y <= -1
    // 0 <= z <= 1

    def f1(p: Point): Double = {
        ((10 * (p.x + p.y)).toInt % 2).toDouble
    }

    def f2(p: Point): Double = {
        (p.x * p.x + p.y * p.y - 1.0).abs
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

case class Point(x: Double, y: Double) {
    override def toString() = "(" + x.toString() + ", " + y.toString() + ")"
}

case class Color(r: Int, g: Int, b: Int) {
    def +(that: Color): Color = {
        Color(this.r + that.r, this.g + that.g, this.b + that.b)
    }

    def *(that: Color): Color = {
        Color(this.r * that.r, this.g * that.g, this.b * that.b)
    }

    override def toString() = "rgb(" + r.toString() + ", " + g.toString() + ", " + b.toString() + ")"
}
