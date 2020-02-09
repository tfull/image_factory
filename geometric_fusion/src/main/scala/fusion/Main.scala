package fusion

import java.io.PrintWriter
import java.util.Date

object Main {
    def main(args: Array[String]) {
        sample()
    }

    def make() {
        val pw = new PrintWriter("output.txt")
        pw.write("Hello, world!\n")
        pw.close()
        val width = 256
        val height = 256

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

    def sample() {
        val time_s = "%tY%<tm%<td_%<tH%<tM%<tS_%<tL".format(new Date())
        val out = new PrintWriter("images/ppm/" + time_s + ".ppm")

        val width = 256
        val height = 256

        out.println("P2")
        out.println("%d %d".format(width, height))
        out.println(255)

        val z_func = (p: Point) => Function.c1(Function.f1(p))

        for (i <- 0 until height) {
            for (j <- 0 until width) {
                val x = -1.0 + (2.0 / width.toDouble) * j + (1.0 / width.toDouble)
                val y = 1.0 - (2.0 / height.toDouble) * i - (1.0 / height.toDouble)
                val p = Point(x, y)
                val z = (z_func(p) * 255 + 0.5).toInt
                if (j == 0) {
                    out.write("%d".format(z))
                } else {
                    out.write(" %d".format(z))
                }
            }
            out.write("\n")
        }
        out.flush()
    }
}
