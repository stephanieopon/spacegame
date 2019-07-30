package cs2.game

import scalafx.application.JFXApp
import cs2.util.Vec2
import scalafx.scene.Scene
import scalafx.scene.layout.GridPane
import scalafx.animation.AnimationTimer
import scalafx.scene.image.Image
import scalafx.scene.canvas.Canvas
import scalafx.scene.input.KeyEvent
import scalafx.scene.control.Button
import scalafx.scene.input.KeyCode
import scalafx.event.ActionEvent
import scalafx.Includes._
import scala.collection.mutable.Buffer
import scalafx.scene.paint.Color
import cs2.adt.LinkedStack
import cs2.adt.LinkedQueue
import cs2.adt.ArrayQueue

/**
 * main object that initiates the execution of the game, including construction
 *  of the window.
 *  Will create the stage, scene, and canvas to draw upon. Will likely contain or
 *  refer to an AnimationTimer to control the flow of the game.
 */
object SpaceGameApp extends JFXApp {
  val bimg = new Image("ezgif.com-crop (6).png")
  val eimg = new Image("ezgif.com-crop (5).png")
  val exp128 = new Image("explosion_128x128.png")
  val exp64 = new Image("explosion_64x64.png")
  val exp32 = new Image("explosion_32x32.png")
  val ssheet = new Image("space_spritesheet.png")
  val nohit = new Image("ezgif.com-crop.png")
  val onehit = new Image("ezgif.com-crop (2).png")
  val twohit = new Image("ezgif.com-crop (3).png")
  val pimg = new Image("file:player1.jpeg")
  val ben = new Image("ezgif.com-crop (4).png")
  val gstr = new Image("greenstr.png")
  val gleft = new Image("greenleft (1).png")
  val gright = new Image("greenright (1).png")
  val ostr = new Image("orangestr.png")
  val oleft = new Image("orangeleft (1).png")
  val oright = new Image("orangeright (1).png")
  val rstr = new Image("redstr.png")
  val rleft = new Image("redleft (1).png")
  val rright = new Image("redright (1).png")
  val bk = new Image("ezgif.com-crop (9).png")
  var keyset = scala.collection.mutable.Set[KeyCode]()
  var lives = 5
  var hit = 0
  val txt = new Image("Untitled document (2).jpg")
  val r1 = new Image("smallrock.png")
  val r2 = new Image("bigrock.png")
  val sf1 = new Image("M39.jpg")
  var pu1 = false
  var pu2 = false
  var pu3 = false
  var limg = new Image("RedLaser2.png")

  stage = new JFXApp.PrimaryStage {
    title = "Space Game!"
    scene = new Scene(800, 800) {
      val canvas = new Canvas(800, 800)
      val g = canvas.graphicsContext2D
      content = canvas

      var multi = 0

      var score = 0
      var level = 1

      var pu = 0

      var laser = false

      var las = Buffer[Laser]()
      // var las = new Laser(limg, new Vec2(0,0), new Vec2(0,0))
      var del = 0
      var time = 0
      var delayamt = 30
      var lst = Buffer[Bullet]()
      var eswarm = new EnemySwarm(3, 5)

      var stack = new LinkedStack[GameState]()
      //var stack = scala.collection.mutable.Stack[GameState]()

      var expbuff = Buffer[Explosion]()
      var benemy = new BossEnemy(ben, new Vec2(380, 200), bimg)

      val player = new Player(pimg, new Vec2(380, 690), bimg)
      var ShowStartScreen = true
      var disp = false
      var que = new ArrayQueue[scala.collection.mutable.Set[KeyCode]]()
      var ebullets = Buffer[Bullet]()
      var bbullets = Buffer[Bullet]()
      var pups = Buffer[PowerUp]()
      var enemy = new Enemy(eimg, new Vec2(0, 0), bimg)
      var backg = new Background(sf1, new Vec2(0, 0), new Vec2(0, 0))

      var gstate = new GameState(player.clone, lives, score, level, ebullets, lst, eswarm,
        expbuff, benemy, bbullets, backg, pups, las)

      var countdown = 20
      var count = 20
      var pucount = 600
      var remove = Buffer[Bullet]()
      var remove2 = Buffer[Laser]()
var ct = 0
      var prevT: Long = 0L
      val timer = AnimationTimer(t => {
        if (t - prevT > 1e9 / 60) {

          if (ShowStartScreen == true) {

            gstate.bgd.display1(g)
            g.fill = Color.White
            g.drawImage(txt, 0, 0, 800, 800)
            g.drawImage(r1, 20, 20, 20, 20)
            g.drawImage(r2, 50, 100, 40, 40)
            g.drawImage(r1, 700, 700, 20, 20)
            g.drawImage(r2, 399, 670, 40, 40)
            g.drawImage(r1, 200, 450, 20, 20)
            g.drawImage(r2, 555, 234, 40, 40)
            g.drawImage(r1, 100, 766, 20, 20)
            g.drawImage(r2, 444, 555, 40, 40)
            g.drawImage(r1, 222, 333, 20, 20)
            g.drawImage(r2, 600, 600, 40, 40)
            g.drawImage(r1, 250, 750, 20, 20)
            g.drawImage(r2, 350, 400, 40, 40)
            g.drawImage(r2, 250, 170, 40, 40)
            g.drawImage(r2, 250, 500, 40, 40)
            g.drawImage(r2, 500, 150, 40, 40)
            g.fill = Color.Black
            g.fillText("P r e s s  a n y  n u m b e r  f r o m  1  t o  5  t o  c h o o s e  l i v e s", 200, 560, 500)
            g.fillText("P r e s s  N  t o  S t a r t  G a m e ", 200, 600, 500)
            g.fillText("P r e s s  Q  t o  Q u i t  G a m e ", 200, 640, 500)

          } else if (lives >= 1) {
            if (keyset.contains(KeyCode.R)) {
              gstate.bgd.display1(g)
              g.fill = Color.White
              g.fillText("lives:" + lives, 700, 740, 50)
              g.fillText("score:" + score, 700, 760, 50)
              g.fillText("level:" + level, 700, 780, 50)
              g.stroke = Color.White
              g.fillText("reverse time:", 680, 25)
              g.fillRect(690, 25, stack.length / 100, 20)
              g.fill = Color.Grey
              g.fillRect(798, 25, 2, 20)

              if (stack.isEmpty == false) { gstate = stack.pop() }
              gstate.enemyswarm.display(g)
              gstate.player.display(g)
              gstate.ebullets.foreach((x: Bullet) => x.display(g))
              gstate.playerbullets.foreach((x: Bullet) => x.display(g))
              gstate.expbuffer.foreach((x: Explosion) => x.display(g))
              gstate.bbullets.foreach((x: Bullet) => x.display(g))
              gstate.bossenemy.display(g)
              gstate.powerup.foreach((x: PowerUp) => x.display(g))
              gstate.lasers.foreach((x:Laser) => x.display(g))
            } else {

              prevT = t
              gstate.bgd.display1(g)

              g.fill = Color.White
              g.fillText("lives:" + lives, 700, 740, 50)
              g.fillText("score:" + score, 700, 760, 50)
              g.fillText("level:" + level, 700, 780, 50)
              g.fillText("time elapsed:" + (time / (60)) + "s", 680, 20)
              g.fillRect(680, 25, stack.length / 100, 20)
              gstate.enemyswarm.display(g)
              gstate.player.display(g)

              gstate.playerbullets.foreach((x: Bullet) => x.display(g))
              gstate.playerbullets.foreach((x: Bullet) => x.timeStep)
              gstate.ebullets.foreach((x: Bullet) => x.display(g))
              gstate.ebullets.foreach((x: Bullet) => x.othertimeStep)
              gstate.expbuffer.foreach((x: Explosion) => x.display(g))
              gstate.expbuffer.foreach((x: Explosion) => x.timeStep)
              gstate.bbullets.foreach((x: Bullet) => x.display(g))
              gstate.bbullets.foreach((x: Bullet) => x.othertimeStep)
              gstate.powerup.foreach((x: PowerUp) => x.display(g))
              gstate.powerup.foreach((x: PowerUp) => x.timeStep)
              if (laser == true && keyset.contains(KeyCode.Enter)) {
                gstate.lasers += gstate.player.shootlaser
                // gstate.lasers.display(g)
                gstate.lasers.foreach((x: Laser) => x.display(g))

              }

              if (math.random() < 0.03) gstate.ebullets += gstate.enemyswarm.shoot
              if (math.random < 0.002 && (disp == false)) disp = true
              if (math.random() < 0.007) gstate.powerup += gstate.enemyswarm.dropPU

              if (disp == true) {
                gstate.bossenemy.display(g)
                for (x <- gstate.playerbullets) {

                  if (gstate.bossenemy.intersection(x)) {
                    hit += 1
                  }
                }

                if (hit == 3) {

                  score += 10
                  disp = false
                  hit = 0
                  gstate.expbuffer += new Explosion(exp128, gstate.bossenemy.pos)
                }
              }

              que.enqueue(keyset.clone())

              var kc: scala.collection.mutable.Set[KeyCode] = null
              if (que.len >= delayamt) {
                kc = que.dequeue
                if ((kc.contains(KeyCode.Left) || kc.contains(KeyCode.A)) && disp == true) { gstate.bossenemy.moveLeft }
                if ((kc.contains(KeyCode.Right) || kc.contains(KeyCode.D)) && disp == true) { gstate.bossenemy.moveRight }
                if ((kc.contains(KeyCode.Up) || kc.contains(KeyCode.W)) && disp == true) { gstate.bossenemy.moveDown }
                if ((kc.contains(KeyCode.Down) || kc.contains(KeyCode.S)) && disp == true) { gstate.bossenemy.moveUp }

                if (kc.contains(KeyCode.Space) && count <= 0 && disp == true) {
                  gstate.bbullets += gstate.bossenemy.shoot
                  count = 20
                }
                count -= 1
              }

              if (keyset.contains(KeyCode.Left) || keyset.contains(KeyCode.A)) { gstate.player.moveLeft }
              if (keyset.contains(KeyCode.Right) || keyset.contains(KeyCode.D)) { gstate.player.moveRight }
              if (keyset.contains(KeyCode.Up) || keyset.contains(KeyCode.W)) { gstate.player.moveUp }
              if (keyset.contains(KeyCode.Down) || keyset.contains(KeyCode.S)) { gstate.player.moveDown }
              if (keyset.contains(KeyCode.Enter) && laser == true) {
               
                gstate.lasers += gstate.player.shootlaser
                gstate.lasers.foreach((x: Laser) => x.display(g))

              }

              if (keyset.contains(KeyCode.Space) && countdown <= 0) {
                gstate.player.shoot
                gstate.playerbullets += gstate.player.shoot
                countdown = 20
              }

              countdown -= 1

              gstate.enemyswarm.move
              var x = 0
              var kill = Buffer[Enemy]()
              var killalso = Buffer[Bullet]()

              //player intersection with enemy: moving player to original position
              for (r <- gstate.enemyswarm.eswarm) {
                for (enemy <- r) {
                  if (gstate.player.intersection(enemy)) { player.moveTo(new Vec2(400, 690)) }
                }
              }

              //player intersection with bossenemy: moving player to original position
               
                  if (gstate.player.intersection(gstate.bossenemy)) { player.moveTo(new Vec2(400, 690)) }
                
              
              
              //player bullet intersection with enemy: explosion, increase score, remove enemy
              for (r <- gstate.enemyswarm.eswarm) {
                for (enemy <- r) {
                  for (x <- gstate.playerbullets) {
                    if (x.intersection(enemy)) {
                      gstate.expbuffer += new Explosion(exp64, enemy.pos)
                      kill += enemy
                      score += 1
                      killalso += x
                    }
                  }
                }
                r --= kill
              }

              //player laser intersection with enemy: explosion, increase score, remove enemy
              for (r <- gstate.enemyswarm.eswarm) {
                for (enemy <- r) {
                  for (x <- gstate.lasers) {
                    if (x.intersection(enemy)) {
                      gstate.expbuffer += new Explosion(exp64, enemy.pos)
                      kill += enemy
                      score += 1

                    }
                  }
                }
                r --= kill
              }

              //player bullet intersection with boss enemy: add enemy to kill buffer
              if (disp == true) {
                for (x <- gstate.playerbullets) {

                  if (x.intersection(gstate.bossenemy)) {

                    killalso += x
                  }
                }
              }

           /* //player laser intersection with boss enemy: add boss enemy to kill buffer
              if (disp == true) {
                for (x <- gstate.lasers ) {

                  if (x.intersection(gstate.bossenemy)) {

                    remove2 += x
                    hit += 1
                  }
                }
              }*/
              
              //player bullet intersection with enemy bullet: add bullets to kill buffer
              for (r <- gstate.ebullets) {
                for (x <- gstate.playerbullets) {
                  if (x.intersection(r)) {
                    killalso += r
                    killalso += x
                  }
                }
              }

              //player bullet intersection with boss enemy bullet: add bullets to kill buffer
              for (r <- gstate.bbullets) {
                for (x <- gstate.playerbullets) {
                  if (x.intersection(r)) {
                    killalso += r
                    killalso += x
                  }
                }
              }

              for (r <- gstate.bbullets) {
                if (gstate.player.intersection(r)) {
                  killalso += r
                  multi += 1
                  if (multi % 2 == 0) {
                    lives -= 1

                    gstate.player.moveTo(new Vec2(380, 690))
                  }

                }
              }

              //remove bullets from buffer
              gstate.ebullets --= killalso
              gstate.playerbullets --= killalso
              gstate.bbullets --= killalso

              //player intersection with enemy bullets: reduce lives, move player to original spot
              var i = 0
              while (i < gstate.ebullets.length) {

                if (gstate.player.intersection(gstate.ebullets(i))) {
                  multi += 1
                  if (multi % 2 == 0) {
                    lives -= 1
                    //expbuff += new Explosion(exp64, player.pos)
                    gstate.player.moveTo(new Vec2(380, 690))
                  }

                  gstate.ebullets -= gstate.ebullets(i)
                }
                i += 1
              }

              var rpup = Buffer[PowerUp]()
              pucount -= 1
              for (r <- gstate.powerup) {
                if (gstate.player.intersection(r)) {
                  rpup += r
                  pu += 1

                  if (pu == 2) {
                    lives += 2
                    
                  
                   
                  } else if (pu == 3) {
                    laser = true
                     
                   
                  } else if (pu == 1) {
                    pu1 = true
                  
                  }
                }
              }

              if (pucount <= 0) {
                pu1 = false
                laser = false
                if (pu >= 3) pu = 0
                pucount = 600
              }


              gstate.powerup --= rpup

            

              //remove bullets when they leave screen
              for (x <- gstate.ebullets) { if (x.out == true) remove += x }
              for (y <- gstate.playerbullets) { if (y.out == true) remove += y }
              if (laser == false && gstate.lasers != null) { for (z <- gstate.lasers) remove2 += z }

              gstate.ebullets --= remove
              gstate.playerbullets --= remove
              gstate.lasers --= remove2
              if (time % 1 == 0) gstate.lasers.clear

              if (gstate.enemyswarm.empty() == true) {
                gstate.enemyswarm = new EnemySwarm(3, 5)
                level += 1
              }

              stack.push(gstate.clone())
              time += 1
            }

          } else {
            if (lives == 0) {
              expbuff += new Explosion(exp128, gstate.player.pos)
              gstate.bgd.display1(g)
              g.fill = Color.White
              g.fillText("lives:" + lives, 700, 740, 50)
              g.fillText("score:" + score, 700, 760, 50)
              g.fillText("level:" + level, 700, 780, 50)
              g.stroke = Color.White
              g.fillText("reverse time remaining:" + (time / (60) - 1), 600, 25)
              g.fillRect(690, 25, stack.length / 100, 20)
              g.fill = Color.Black
              g.fillRect(798, 25, 2, 20)

              gstate.enemyswarm.display(g)
              gstate.bossenemy.display(g)
              gstate.ebullets.foreach((x: Bullet) => x.display(g))
              gstate.playerbullets.foreach((x: Bullet) => x.display(g))
              gstate.expbuffer.foreach((x: Explosion) => x.display(g))
              gstate.bbullets.foreach((x: Bullet) => x.display(g))
              gstate.expbuffer.foreach((x: Explosion) => x.timeStep)
              gstate.powerup.foreach((x: PowerUp) => x.display(g))

              del += 1
            }
            if (lives == 0 && del >= 80) {
              gstate.bgd.display1(g)
              g.fill = Color.White
              g.fillText("l i v e s : " + lives, 350, 350, 500)
              g.fillText("s c o r e : " + score, 350, 380, 500)
              g.fillText("P r e s s  a n y  n u m b e r  f r o m  1  t o  5  t o  c h o o s e  l i v e s,  t h e n  p r e s s  N  f o r  a  n e w  g a m e", 100, 410, 750)
              g.fillText("P r e s s  Q  t o  Q u i t  G a m e ", 300, 440, 500)
            }
          }

        }

      })
      timer.start
      canvas.requestFocus()
      canvas.onKeyPressed =
        (e: KeyEvent) => {

          if (e.code == KeyCode.Left) {
            keyset += KeyCode.Left
          } else if (e.code == KeyCode.Right) {
            keyset += KeyCode.Right
          } else if (e.code == KeyCode.Space) {
            keyset += KeyCode.Space
          } else if (e.code == KeyCode.Up) {
            keyset += KeyCode.Up
          } else if (e.code == KeyCode.Down) {
            keyset += KeyCode.Down
          } else if (e.code == KeyCode.Enter) {
            keyset += KeyCode.Enter
          } else if (e.code == KeyCode.N) {
            ShowStartScreen = false
            //lives = 5
            score = 0
            del = 0
            gstate.ebullets.clear()
            gstate.bbullets.clear()
            gstate.playerbullets.clear()
            gstate.enemyswarm = new EnemySwarm(3, 5)
            gstate.expbuffer.clear()
            stack.length = 0
            time = 0

          } else if (e.code == KeyCode.R) {
            keyset += KeyCode.R
          } else if (e.code == KeyCode.W) {
            keyset += KeyCode.W
          } else if (e.code == KeyCode.A) {
            keyset += KeyCode.A
          } else if (e.code == KeyCode.S) {
            keyset += KeyCode.S
          } else if (e.code == KeyCode.D) {
            keyset += KeyCode.D
          } else if (e.code == KeyCode.Q) {
            System.exit(0)
          } else if (e.code == KeyCode.Digit1) {
            lives = 1
            score = 0
            del = 0
            gstate.ebullets.clear()
            gstate.bbullets.clear()
            gstate.playerbullets.clear()
            gstate.enemyswarm = new EnemySwarm(3, 5)
            gstate.expbuffer.clear()
            disp = false
            stack.length = 0
            time = 0
            hit = 0
          } else if (e.code == KeyCode.Digit2) {
            lives = 2
            score = 0
            del = 0
            gstate.ebullets.clear()
            gstate.bbullets.clear()
            gstate.playerbullets.clear()
            gstate.enemyswarm = new EnemySwarm(3, 5)
            gstate.expbuffer.clear()
            disp = false
            stack.length = 0
            time = 0
            hit = 0
          } else if (e.code == KeyCode.Digit3) {
            lives = 3
            score = 0
            del = 0
            gstate.ebullets.clear()
            gstate.bbullets.clear()
            gstate.playerbullets.clear()
            gstate.enemyswarm = new EnemySwarm(3, 5)
            gstate.expbuffer.clear()
            disp = false
            stack.length = 0
            time = 0
            hit = 0
          } else if (e.code == KeyCode.Digit4) {
            lives = 4
            score = 0
            del = 0
            gstate.ebullets.clear()
            gstate.bbullets.clear()
            gstate.playerbullets.clear()
            gstate.enemyswarm = new EnemySwarm(3, 5)
            gstate.expbuffer.clear()
            disp = false
            stack.length = 0
            time = 0
            hit = 0
          } else if (e.code == KeyCode.Digit5) {
            lives = 5
            score = 0
            del = 0
            gstate.ebullets.clear()
            gstate.bbullets.clear()
            gstate.playerbullets.clear()
            gstate.enemyswarm = new EnemySwarm(3, 5)
            gstate.expbuffer.clear()
            disp = false
            stack.length = 0
            time = 0
            hit = 0
          }
        }

      canvas.onKeyReleased =
        (e: KeyEvent) => {
          if (e.code == KeyCode.Left) {
            keyset -= KeyCode.Left
          } else if (e.code == KeyCode.Right) {
            keyset -= KeyCode.Right
          } else if (e.code == KeyCode.Space) {
            keyset -= KeyCode.Space
          } else if (e.code == KeyCode.Up) {
            keyset -= KeyCode.Up
          } else if (e.code == KeyCode.Down) {
            keyset -= KeyCode.Down
          } else if (e.code == KeyCode.R) {
            keyset -= KeyCode.R
          } else if (e.code == KeyCode.W) {
            keyset -= KeyCode.W
          } else if (e.code == KeyCode.A) {
            keyset -= KeyCode.A
          } else if (e.code == KeyCode.S) {
            keyset -= KeyCode.S
          } else if (e.code == KeyCode.D) {
            keyset -= KeyCode.D
          } else if (e.code == KeyCode.Enter) {
            keyset -= KeyCode.Enter
            laser = false
          }
        }
    }
  }
}