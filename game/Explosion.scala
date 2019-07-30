package cs2.game

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image

class Explosion(img: Image, pos: Vec2) extends Sprite(img, pos) {

  var x = 0
  var y = 0
  def width = 0
  def height = 0

  override def display(g: GraphicsContext): Unit = {
    g.drawImage(img, x, y, (img.width.value / 8), (img.height.value / 6), pos.x, pos.y,
                (img.height.value / 8), (img.height.value / 6))
  }

  def timeStep() {
    if (x <= img.width.value && y <= img.height.value) {
      x += (img.width.value / 8).toInt

    }
    if (x == img.width.value) {
      y += (img.height.value / 6).toInt
      x = 0
    }
  }

  override def clone(): Explosion = {
    var exp = new Explosion(img, new Vec2(pos))
    exp.x = this.x
    exp.y = this.y
    exp
  }

}