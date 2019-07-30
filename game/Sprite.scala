package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

/** A graphical sprite object used for gaming or other visual displays
 *  
 *  @constructor create a new sprite based on the given image and initial location
 *  @param img the image used to display this sprite
 *  @param pos the initial position of the '''center''' of the sprite in 2D space
 */
abstract class Sprite (protected val img:Image,  var pos:Vec2/*, protected var width:Int, protected var height:Int*/) {
val v = 50
  /** moves the sprite a relative amount based on a specified vector
   *  
   *  @param direction - an offset that the position of the sprite should be moved by
   *  @return none/Unit
   */
  def move (direction:Vec2) { 
       pos += direction 
    
  }
  def width():Int
  def height():Int
  /** moves the sprite to a specific location specified by a vector (not a relative movement)
   *  
   *  @param location - the new location for the sprite's position
   *  @return none/Unit
   */
  def moveTo (location:Vec2) { 
   pos = location
  }
  
  def position = pos
  
  //def clonepos = this.pos
  
  def intersection (other:Sprite): Boolean = { 
    
   if  ((this.pos.x+this.width() >= other.pos.x) && (this.pos.x <= other.pos.x+other.width()) && 
       (this.pos.y<= other.pos.y+other.height()) && (this.pos.y+this.height() >= other.pos.y)) {
     true
   }
   else false
 }
  
  /** Method to display the sprite at its current location in the specified Graphics2D context
   *  
   *  @param g - a GraphicsContext object capable of drawing the sprite
   *  @return none/Unit
   */
 def display (g:GraphicsContext) {
   g.drawImage(img,pos.x,pos.y )
  }
  
}