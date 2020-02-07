package src

case class Color(r: Int, g: Int, b: Int) {
    def +(that: Color): Color = {
        Color(this.r + that.r, this.g + that.g, this.b + that.b)
    }

    def *(that: Color): Color = {
        Color(this.r * that.r, this.g * that.g, this.b * that.b)
    }

    override def toString() = "rgb(" + r.toString() + ", " + g.toString() + ", " + b.toString() + ")"
}
