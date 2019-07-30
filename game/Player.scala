package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.input.KeyCode

/**
 * The player representation for a simple game based on sprites. Handles all
 *  information regarding the player's positions, movements, and abilities.
 *
 *  @param avatar the image representing the player
 *  @param initPos the initial position of the '''center''' of the player
 *  @param bulletPic the image of the bullets fired by this player
 */
class Player(avatar: Image, initPos: Vec2, private val bulletPic: Image /*, width:Int, height:Int*/ )
  extends Sprite(avatar, initPos /*, width, height*/ ) with ShootsBullets {

  /**
   * moves the player sprite one "step" to the left.  The amount of this
   *  movement will likely need to be tweaked in order for the movement to feel
   *  natural.
   *
   *  @return none/Unit
   */
var keyset = SpaceGameApp.keyset
//scala.collection.mutable.Set[KeyCode]()
//  var lives = SpaceGameApp.lives
  //val bimg = new Image("file:CzgBEKQWIAEh0Ez.jpg_large")

  override def display(g: GraphicsContext): Unit = {
   if (SpaceGameApp.lives >= 5 && (keyset.contains(KeyCode.Left) || keyset.contains(KeyCode.A))){
                g.drawImage(SpaceGameApp.gleft,pos.x, pos.y, 100, 100)
              }
              if (SpaceGameApp.lives >= 5 && (keyset.contains(KeyCode.Right) || keyset.contains(KeyCode.D))) {
                g.drawImage(SpaceGameApp.gright, pos.x,pos.y, 100, 100)
              }
              if (SpaceGameApp.lives >= 5 && !(keyset.contains(KeyCode.Right) || keyset.contains(KeyCode.D) || 
                  keyset.contains(KeyCode.Left) ||keyset.contains(KeyCode.A))) {
                g.drawImage(SpaceGameApp.gstr,pos.x, pos.y, 100, 100)
              }
              
                 if (SpaceGameApp.lives <= 2 && (keyset.contains(KeyCode.Left) || keyset.contains(KeyCode.A))) {
                g.drawImage(SpaceGameApp.rleft,pos.x, pos.y, 100, 100)
              }
              if (SpaceGameApp.lives <= 2 && (keyset.contains(KeyCode.Right) || keyset.contains(KeyCode.D))) {
                g.drawImage(SpaceGameApp.rright, pos.x, pos.y, 100, 100)
              }
              if (SpaceGameApp.lives <= 2 && !(keyset.contains(KeyCode.Right) || keyset.contains(KeyCode.D) || 
                  keyset.contains(KeyCode.Left) || keyset.contains(KeyCode.A))) {
                g.drawImage(SpaceGameApp.rstr, pos.x, pos.y, 100, 100)
              }

              if (SpaceGameApp.lives < 5 && SpaceGameApp.lives > 2 && (keyset.contains(KeyCode.Left) || keyset.contains(KeyCode.A))){
                g.drawImage(SpaceGameApp.oleft, pos.x, pos.y, 100, 100)
              }
              if (SpaceGameApp.lives < 5 && SpaceGameApp.lives > 2 && (keyset.contains(KeyCode.Right) || keyset.contains(KeyCode.D))){
                g.drawImage(SpaceGameApp.oright,pos.x, pos.y, 100, 100)
              }
              if (SpaceGameApp.lives < 5 && SpaceGameApp.lives > 2 && !(keyset.contains(KeyCode.Right) || keyset.contains(KeyCode.D) || 
                  keyset.contains(KeyCode.Left) || keyset.contains(KeyCode.A))) {
                g.drawImage(SpaceGameApp.ostr, pos.x, pos.y, 100, 100)
              }

           

  
    
  }

  def moveLeft() {
    if (SpaceGameApp.pu1 == true) {
      if (pos.x > 0) pos += new Vec2(-4, 0)
    }
    else { if (pos.x > 0) pos += new Vec2(-2, 0) }

  }

  def moveRight() {
   if (SpaceGameApp.pu1 == true) {
    if (pos.x < 700) pos += new Vec2(4, 0)
   } else {if (pos.x < 700) pos += new Vec2(2, 0)}
  }

  def moveUp() {
    
   if (SpaceGameApp.pu1 == true) {
      if (pos.y > 0) pos += new Vec2(0, -4)
    }
    else { if (pos.y > 0) pos += new Vec2(0, -2) }


  }

  def moveDown() {
    if (SpaceGameApp.pu1 == true) {
    if (pos.y < 700) pos += new Vec2(0, 4)
   } else {if (pos.y < 700) pos += new Vec2(0, 2)}
  }

  

  /**
   * creates a new Bullet instance beginning from the player, with an
   *  appropriate velocity
   *
   *  @return Bullet - the newly created Bullet object that was fired
   */
  def shoot(): Bullet = {

    new Bullet(SpaceGameApp.bimg, new Vec2(pos.x, pos.y), new Vec2(5, 5))

  }
  
  def shootlaser(): Laser = {
    new Laser(SpaceGameApp.limg,new Vec2(pos.x,pos.y), new Vec2(0,0))
  }

  def width() = 100
  def height() = 100
  override def clone(): Player = {
    new Player(img, new Vec2(pos), SpaceGameApp.bimg)
  }

}