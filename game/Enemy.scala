package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

/** An enemy representation for a simple game based on sprites. Handles all 
 *  information regarding the enemy's position, movements, and abilities.
 *  
 *  @param pic the image representing the enemy
 *  @param initPos the initial position of the '''center''' of the enemy
 *  @param bulletPic the image of the bullets fired by this enemy
 */
class Enemy(pic:Image, initPos:Vec2, private val bulletPic:Image) 
                  extends Sprite(pic, initPos) with ShootsBullets {
  
  

  
  override def display(g:GraphicsContext):Unit = {
    g.drawImage(img, pos.x, pos.y,40,40)
 }
  
 def width() = 40
 def height() = 40
 

  /** creates a new Bullet instance beginning from this Enemy, with an appropriate velocity
   * 
   *  @return Bullet - the newly created Bullet object that was fired
   */
  def shoot():Bullet = { 
 
    
    new Bullet(SpaceGameApp.bimg, new Vec2(pos.x,pos.y), new Vec2(5,5))
    
  }
 
  def dropPU():PowerUp = { 
 
    
    new PowerUp(SpaceGameApp.r2, new Vec2(pos.x,pos.y), new Vec2(5,5))
    
  }
 
 
 override def clone():Enemy = {
   new Enemy(img, new Vec2(pos), SpaceGameApp.bimg)
 }
 
}