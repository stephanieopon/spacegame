package cs2.game

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image

 class Laser(pic: Image, initPos: Vec2, private var vel: Vec2) extends Sprite(pic, initPos) {

  override def display(g: GraphicsContext): Unit = {
   g.drawImage(SpaceGameApp.limg, pos.x-60,0,SpaceGameApp.limg.width.value-60,pos.y+80 )//,20,initPos.y)//,50,initPos.y)//-50, 15, initPos.y)
    
  }

 
  
  def timeStep() {
    initPos += new Vec2(0, -4)

  }

  
  def laserout(): Boolean = {
    if (pos.x > 850 || pos.x < -50 || pos.y > 1000 || pos.y < -350) true
    else false
  }
  
  override def intersection (other:Sprite): Boolean = { 
    
   if  ((this.pos.x+this.width() >= other.pos.x) && (this.pos.x <= other.pos.x+other.width()) && 
       (0<= other.pos.y+other.height()) && (this.pos.y >= other.pos.y)) {
     true
   }
   else false
 }
  

  def width() = SpaceGameApp.limg.width.value.toInt-170
  def height() = SpaceGameApp.limg.height.value.toInt

  
  
  def clonel():Laser = {
   new Laser(img, new Vec2(pos), new Vec2(vel))

 }

}
 
