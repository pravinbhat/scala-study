object Collections {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // Tuples
  val t = ("Hello",2)                             //> t  : (String, Int) = (Hello,2)
  
  // Decomposition and assignment
  val (text,value) = t                            //> text  : String = Hello
                                                  //| value  : Int = 2
  assert(text == "Hello")
  assert(value == 2)

	case class TLike(text : String, value : Int)
	
	val tl = TLike("HelloAgain",5)            //> tl  : Collections.TLike = TLike(HelloAgain,5)
	
	val TLike(newText,newValue) = tl          //> newText  : String = HelloAgain
                                                  //| newValue  : Int = 5
  
  // How about some lists
  val a = 1 :: 10 :: 100 :: 1000 :: 10000 :: Nil  //> a  : List[Int] = List(1, 10, 100, 1000, 10000)
  
  val c = Nil.::(10000).::(1000).::(100)          //> c  : List[Int] = List(100, 1000, 10000)
  
  val b = 2 :: 3  :: 4   :: 5    :: 6     :: 7 :: Nil
                                                  //> b  : List[Int] = List(2, 3, 4, 5, 6, 7)
  
  // The standard implementation of a Sequence is a list.
  val k = Seq("Ones","Tens","Hundreds","Thousands","Ten Thousands")
                                                  //> k  : Seq[String] = List(Ones, Tens, Hundreds, Thousands, Ten Thousands)
  
  // Reduce the Int Collections, note the low calorie method call
  val r : Int = a reduce (_+_)                    //> r  : Int = 11111
  
  // Same as the actual call with the dot operator
  assert( r == a.reduce(_+_) )
  
  // Try a fold
  val rfold = a.foldLeft(100000000)(_+_)          //> rfold  : Int = 100011111
  
  // A foldLeft can be thought of as a reduce operation with a value first prepended to the list.
  val fakeReduce = (100000000 :: a).reduce(_+_)   //> fakeReduce  : Int = 100011111
  
  // Now lets combine two sequences element by element
  val ab = b zip a //a zip b                      //> ab  : List[(Int, Int)] = List((2,1), (3,10), (4,100), (5,1000), (6,10000))
  
  // How about a product element by element of our two lists. Note the funny variable name. Anything is valid as long as it's in backticks
  val `a*b` = ab.map( x => x._1 * x._2 )          //> a*b  : List[Int] = List(2, 30, 400, 5000, 60000)
  
  // Let's to the product of two lists in one line!
  a zip b map(x => x._1 * x._2)                   //> res0: List[Int] = List(2, 30, 400, 5000, 60000)
  
  // combine String list with Int list (get list of Tuples)
  // Notice how it works just like a zipper!
  val `k-b` = k zip a                             //> k-b  : Seq[(String, Int)] = List((Ones,1), (Tens,10), (Hundreds,100), (Thou
                                                  //| sands,1000), (Ten Thousands,10000))
  // Let's combine the elements of k-b with an '='
  `k-b`.map(x => x._1 + "=" + x._2).mkString(",") //> res1: String = Ones=1,Tens=10,Hundreds=100,Thousands=1000,Ten Thousands=100
                                                  //| 00
  // Now let's do it all in one line!
  k zip a map (x => x._1 + "=" + x._2) mkString(",")
                                                  //> res2: String = Ones=1,Tens=10,Hundreds=100,Thousands=1000,Ten Thousands=100
                                                  //| 00
  
  // A list of tuples is naturally a map
  val kaMap = `k-b`.toMap                         //> kaMap  : scala.collection.immutable.Map[String,Int] = Map(Thousands -> 1000
                                                  //| , Ones -> 1, Tens -> 10, Ten Thousands -> 10000, Hundreds -> 100)
  
  val kaSeq = kaMap map ( (x : (String,Int)) => x._1 + "='" + x._2 + "'")
                                                  //> kaSeq  : scala.collection.immutable.Iterable[String] = List(Thousands='1000
                                                  //| ', Ones='1', Tens='10', Ten Thousands='10000', Hundreds='100')
  val kwhere = kaSeq.mkString(" and ")            //> kwhere  : String = Thousands='1000' and Ones='1' and Tens='10' and Ten Thou
                                                  //| sands='10000' and Hundreds='100'
  
}