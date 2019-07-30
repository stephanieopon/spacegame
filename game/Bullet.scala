package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

/**
 * Representation of a bullet/projectile for a simple game based on sprites.
 *  Handles all information regarding a bullet's position, movements, and
 *  abilities.
 *
 *  @param pic the image representing the bullet
 *  @param initPos the initial position of the '''center''' of the bullet
 *  @param vel the initial velocity of the bullet
 */
class Bullet(pic: Image, initPos: Vec2, private var vel: Vec2) extends Sprite(pic, initPos) {

  override def display(g: GraphicsContext): Unit = {
    g.drawImage(img, pos.x, pos.y, 10, 15)
  }

  /**
   * advances the position of the Bullet over a single time step
   *
   *  @return none/Unit
   */
  def timeStep() {
    initPos += new Vec2(0, -4)

  }

  def othertimeStep() {
    initPos += new Vec2(0, 3)
  }

  def width() = 10
  def height() = 15

  def out(): Boolean = {
    if (pos.x > 850 || pos.x < -50 || pos.y > 850 || pos.y < -50) true
    else false
  }
  
 override def clone():Bullet = {
   new Bullet(img, new Vec2(pos), new Vec2(vel))
 }

}

