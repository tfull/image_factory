package fusion

import scala.util.Random
import scala.math

object Modulator {

    def inversion(x: Double): Double = {
        1 - x
    }

    def geta(x: Double): Double = {
        x / 2 + 0.5
    }

    def loss(x: Double): Double = {
        val threshold = 12
        val random_value = Random.nextInt(100)

        if (threshold > random_value) {
            0.0
        } else {
            x
        }
    }

    def gamma(x: Double): Double = math.pow(x, 1.0 / 2.2)

    val at = Array(inversion _, geta _, loss _, gamma _)

}
