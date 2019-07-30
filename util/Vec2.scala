package cs2.util

class Vec2(var x:Double, var y:Double) {
	//UNCOMMENT THE CODE BELOW AND COMPLETE THE METHODS

	//Default constructor creates a Vec2 of zeroes
  def this() = { 
    this (0.0, 0.0)
  }

  //Copy constructor create a "deep" copy of the input object
  def this(toCopy:Vec2) = this(toCopy.x, toCopy.y)
  

  //Provided toString method simplifies printing, e.g. println(vec.toString) OR println(vec)
	override def toString():String = "("+x+","+y+")"

	//Methods for addition and subtraction of vectors
	def + (other:Vec2):Vec2 = {new Vec2 (this.x+other.x, this.y+other.y)}
	def - (other:Vec2):Vec2 = {new Vec2 (this.x-other.x, this.y-other.y)}

	def += (other:Vec2) =  {this.x = this.x + other.x
	                        this.y = this.y + other.y
	}
	def -= (other:Vec2) = {this.x = this.x - other.x
	                        this.y = this.y - other.y
	}

	//Methods for multiplication and division of vectors by a scalar (non-vector)
	def * (scalar:Double):Vec2 = {new Vec2 (this.x*scalar, this.y*scalar)}
	def / (scalar:Double):Vec2 = {new Vec2 (this.x/scalar, this.y/scalar)}

	def *= (scalar:Double) =  {this.x = this.x*scalar
	                          this.y = this.y*scalar}
	def /= (scalar:Double) = {this.x = this.x/scalar
	                          this.y = this.y/scalar}

	//Methods to determine the length of a vector (magnitude and length should return the same value)
	def magnitude():Double = math.sqrt(math.pow(this.x,2)+ math.pow(this.y,2))
	def length():Double = math.sqrt(math.pow(this.x,2)+ math.pow(this.y,2))

	//Method to return a new vector that is in the same direction, but length 1
	def normalize():Vec2 = {new Vec2(this.x/magnitude(), this.y/magnitude())}
	def unary_~() = {new Vec2(this.x/magnitude(), this.y/magnitude())}

	//Methods to calculate the dot product, and determine the angle between 2 vectors
	def dot(other:Vec2):Double = (this.x*other.x + this.y*other.y)
	def **(other:Vec2):Double = (this.x*other.x + this.y*other.y)

	def angleBetween(other:Vec2):Double = {math.acos((this.x*other.x + this.y*other.y) / (this.magnitude*other.magnitude))}
	def <>(other:Vec2):Double = {math.acos((this.x*other.x + this.y*other.y) / (this.magnitude*other.magnitude))}
	
}

/*object Vec2 {
  def main (args:Array[String]) : Unit = { 
    
    var v = new Vec2(2,7)
    var b = new Vec2(3,9)
    
    println (v+b)
    println (v-b)
   // v-=b
    println (v)
 
    println (b*2)
    println (b/2)
  //b/=2
    println (b)
    
  println (v.magnitude)
  println (v dot b)
  var t = v <> b
  println (t)
  
  println(v.normalize)
 
    
    
  }
}*/
