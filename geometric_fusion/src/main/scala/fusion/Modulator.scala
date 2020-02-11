package fusion

object Modulator {

    def inversion(x: Double): Double = {
        1 - x
    }

    def geta(x: Double): Double = {
        x / 2 + 0.5
    }

    val at = Array(inversion _, geta _)

}
