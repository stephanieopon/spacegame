package cs2.game

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image

class PowerUp(pic: Image, initPos: Vec2, private var vel: Vec2) extends Sprite(pic, initPos) {

  override def display(g: GraphicsContext): Unit = {
    g.drawImage(img, pos.x, pos.y, 20, 20)
  }

  /**
   * advances the position of the Bullet over a single time step
   *
   *  @return none/Unit
   */
  
  def timeStep() {
    initPos += new Vec2(0, 3)
  }

  def width() = 20
  def height() = 20

  def out(): Boolean = {
    if (pos.x > 850 || pos.x < -50 || pos.y > 850 || pos.y < -50) true
    else false
  }
  
  def cloneup():PowerUp = {
   new PowerUp(img, new Vec2(pos), new Vec2(vel))
   
     }

}

