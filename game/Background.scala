package cs2.game

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image
import scala.collection.mutable.Buffer

class Background(pic: Image, initPos: Vec2, private var vel: Vec2) extends Sprite(pic, initPos) {
  var x = -800
  var y = 0
  var u = 0
  var w = 800

  def display1(g: GraphicsContext): Unit = {
    g.drawImage(SpaceGameApp.sf1, 0, y, 800, 800)
    y -= 1
    if (y + 800 == 0) {
      y = 800
    }
    g.drawImage(SpaceGameApp.sf1, 0, w, 800, 800)
    w -= 1
    if (w + 800 == 0) {
      w = 800
    }

  }


  def width = 800
  def height = 800

  def clonebgd(): Background = {
    
    var b = new Background(SpaceGameApp.sf1, new Vec2(pos), new Vec2(vel))
    b.y = this.y 
    b.w = this.w
    b
    
  }

 
  

}