package cs2.game

import cs2.util.Vec2
import scalafx.scene.image.Image
import scalafx.scene.canvas.GraphicsContext

class BossEnemy(pic:Image, initPos:Vec2, private val bulletPic:Image) 
                  extends Sprite(pic, initPos) with ShootsBullets  {
  
 
  
  def width = 110
  def height = 110
 
  var x = 100
  var y = 100
  
  
override def display(g:GraphicsContext):Unit = {
    
    if (SpaceGameApp.hit == 0) g.drawImage(SpaceGameApp.nohit, pos.x,pos.y,110,110) 
    if (SpaceGameApp.hit == 1) g.drawImage(SpaceGameApp.onehit, pos.x,pos.y,110,110)
    if (SpaceGameApp.hit == 2) g.drawImage(SpaceGameApp.twohit,pos.x,pos.y,110,110)
 }  
  

  
 def shoot():Bullet = { 
    new Bullet(SpaceGameApp.bimg, new Vec2(pos.x,pos.y), new Vec2(5,5))  
  }
  
  
   def cloneboss():BossEnemy = {
   new BossEnemy(img, new Vec2(pos), SpaceGameApp.bimg)
 }
 
  
def moveLeft() { 
    if (pos.x > 0) pos += new Vec2(-2,0)
      }
  
  
  def moveRight() { 
    var imgpos = initPos
    if (pos.x < 750)pos += new Vec2(2,0)
  }
  
   def moveUp() { 
    var imgpos = initPos
    if (pos.y > 0) pos += new Vec2(0,-2)
      }
   
    def moveDown() { 
    if (pos.y < 750) pos += new Vec2(0,2)
      }

}

