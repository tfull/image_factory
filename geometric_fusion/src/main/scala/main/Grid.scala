package main

import java.io.PrintWriter
import java.util.Date

import fusion._

object Grid {

    def main(args: Array[String]) {

        val width = 256
        val height = 256

        val (transformers, function, modulators, path) = parseArguments(args)

        val out = new PrintWriter(path)

        out.println("P2")
        out.println("%d %d".format(width, height))
        out.println(255)

        val e = Constant.unit_value

        for (i <- 0 until height) {
            for (j <- 0 until width) {
                val x = - e + (2.0 * e / width.toDouble) * j + (e / width.toDouble)
                val y = e - (2.0 * e / height.toDouble) * i - (e / height.toDouble)
                var p = Point(x, y)

                for (f_i <- transformers) {
                    p = Transformer.at(f_i)(p)
                }

                var c = Function.at(function)(p)

                for (f_i <- modulators) {
                    c = Modulator.at(f_i)(c)
                }

                val z = (c * 255 + 0.5).toInt
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

    def parseArguments(args: Array[String]): (Array[Int], Int, Array[Int], String) = {

        var index = 0

        var transformers: Array[Int] = Array()
        var function: Int = -1
        var modulators: Array[Int] = Array()
        var path: String = ""

        while (index < args.length) {

            args(index) match {
                case "-t" | "--transformers" => {
                    index += 1

                    if (index >= args.length) {
                        throw new Exception("invalid arguments")
                    }

                    transformers = args(index).split(",").map(x => x.toInt)
                }
                case "-f" | "--function" => {
                    index += 1

                    if (index >= args.length) {
                        throw new Exception("invalid arguments")
                    }

                    function = args(index).toInt
                }
                case "-m" | "--modulators" => {
                    index += 1

                    if (index >= args.length) {
                        throw new Exception("invalid arguments")
                    }

                    modulators = args(index).split(",").map(x => x.toInt)
                }
                case "-p" | "--path" => {
                    index += 1

                    if (index >= args.length) {
                        throw new Exception("invalid arguments")
                    }

                    path = args(index)
                }
            }

            index += 1
        }

        if (function == -1) {
            throw new Exception("no function")
        }

        if (path == "") {
            path = "images/ppm/grid_%tY%<tm%<td_%<tH%<tM%<tS_%<tL.ppm".format(new Date())
        }

        (transformers, function, modulators, path)
    }

}
