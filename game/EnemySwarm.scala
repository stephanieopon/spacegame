package cs2.game

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image
import scala.collection.mutable.Buffer
import scalafx.scene.canvas.Canvas

/**
 * contains the control and logic to present a coordinated set of Enemy objects.
 *  For now, this class generates a "grid" of enemy objects centered near the
 *  top of the screen.
 *
 *  @param nRows - number of rows of enemy objects
 *  @param nCols - number of columns of enemy objects
 */
class EnemySwarm(private val nRows: Int, private val nCols: Int) extends ShootsBullets {

  
  

      

   //val eimg = new Image("file:enemy1.jpeg")
    //val bimg = new Image("file:CzgBEKQWIAEh0Ez.jpg_large")
    val enemy = new Enemy(SpaceGameApp.eimg, new Vec2(5, 5), SpaceGameApp.bimg)
    var eswarm = Buffer.fill[Enemy](nRows, nCols)(enemy)
    val rnd = new scala.util.Random
    var x = 175
    var y = 50
    for (r <- 0 until nRows) {
      for (c <-0 until nCols) {
     eswarm(r)(c) = new Enemy(SpaceGameApp.eimg, new Vec2(x,y), SpaceGameApp.bimg)
        x += 90
       
      }
      x = 175
      y += 55
    }
  
  /**
   * method to display all Enemy objects contained within this EnemySwarm
   *
   *  @param g - the GraphicsContext to draw into
   *  @return none/Unit
   */
  def display(g: GraphicsContext) {
    
    for (r <- 0 until nRows) {
      for (c <-0 until eswarm(r).length) {
    
       eswarm(r)(c).display(g)
      }
      
    }
  }

  def move() {
    var x = eswarm.length
   
    for (r <- 0 until nRows) {
      for (c <-0 until eswarm(r).length) {
    
       eswarm(r)(c).move(new Vec2(-1 + rnd.nextInt(3),0.25))
      }
      
    }
  
    
  }
  
  /**
   * overridden method of ShootsBullets. Creates a single, new bullet instance
   *  originating from a random enemy in the swarm. (Not a bullet from every
   *  object, just a single from a random enemy)
   *
   *  @return Bullet - the newly created Bullet object fired from the swarm
   */
 def shoot(): Bullet= {
   
  var t = rnd.nextInt(3)
  var s = rnd.nextInt(5)
   
  while (eswarm(t).length <= s){
    t = rnd.nextInt(3)
    s= rnd.nextInt(5)
  }
      eswarm(t)(s).shoot() 
  
   
  }
 
 
  def dropPU(): PowerUp= {
   
  var t = rnd.nextInt(3)
  var s = rnd.nextInt(5)
   
  while (eswarm(t).length <= s){
    t = rnd.nextInt(3)
    s= rnd.nextInt(5)
  }
      eswarm(t)(s).dropPU() 
  
   
  }

 
def cloneswarm():EnemySwarm = {

  
  var sw = new EnemySwarm(3,5)
   for (r <- 0 until nRows) {
      for (c <-0 until eswarm(r).length) {
     sw.eswarm(r)(c) = eswarm(r)(c).clone
      x += 90
       
      }
      x = 175
      y += 55
    }
   sw
  
}
 
 def empty () : Boolean = {
   var empty = false
   for (r<-eswarm) {
     if (r.isEmpty) empty = true else false
     
   }
   empty
 }
 

}