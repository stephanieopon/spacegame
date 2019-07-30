package cs2.game

import scala.collection.mutable.Buffer
import scalafx.scene.canvas.GraphicsContext

class GameState (val player:Player, val lives:Int, val score:Int, val level:Int,
                  val ebullets:Buffer[Bullet],val playerbullets:Buffer[Bullet],
                  var enemyswarm:EnemySwarm, val expbuffer:Buffer[Explosion],
                   val bossenemy:BossEnemy, val bbullets:Buffer[Bullet],
                   val bgd:Background, val powerup:Buffer[PowerUp], val lasers:Buffer[Laser]){
  
  

     override def clone():GameState = {
     new GameState (
         this.player.clone, this.lives,this.score, 
         this.level, this.ebullets.map(_.clone),
         this.playerbullets.map(_.clone), 
         this.enemyswarm.cloneswarm, 
         this.expbuffer.map((i: Explosion) => i.clone),
         this.bossenemy.cloneboss,
         this.bbullets.map(_.clone),
         this.bgd.clonebgd(),
        this.powerup.map(_.cloneup),
        this.lasers.map(_.clonel))
     }  
  
}