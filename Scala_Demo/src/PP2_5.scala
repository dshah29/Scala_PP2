import util.Random._
//import scala.collection.parallel.mutable.ParTrieMap.Size

class Random_demo 
{
  var R = scala.util.Random.shuffle(1 to 7)
  val LST = List(R.seq(0),R.seq(1),R.seq(2)).sorted.toList
  
  println(LST)
  def Random_num_generator
  {
    var count:Int = 0
    var r = scala.util.Random.shuffle(1 to 7)
    val lst = List(r.seq(0),r.seq(1),r.seq(2))
    for (i <- 0 to 2) 
    {
      if(LST.contains(r.seq(i))) count = count + 1
      if(count == 3) Random_num_generator
    }
    println("Member of a list :"+lst+"\tNumber of perpetuator in list : "+count)
    println("Y or N")
    condition(readLine)
  }
 def condition(a:String)
 {
    if(a == "Y") 
    {
      println("Please enter your answer(ex: 1 2 3)")
      check(readLine)
    }
    else if(a == "N" )
      Random_num_generator
    else println("Wrong Input.. Please re enter")
      condition(readLine)
  }
  def check(a:String)
  {
    val temp = StringBuilder.newBuilder
    LST.addString(temp," ")
    val temp2 = temp.toString()
    //println(temp2)
    println(a.equals(temp2))
   // println(a)
    //val y = LST.toString() 
//    if(x.equals(LST))
//    {
//        println("True")
//    }
//    else println("False")
//    println(x)
//    println(LST)
    //println(a.compareTo(y))
   // println(LST.toList.equals(a.split(" ").toList))
    //println(LST)
  }
}

object PP2_5
{
  def main(args:Array[String])
  {
    var q = new Random_demo
    q.Random_num_generator
  }
}
