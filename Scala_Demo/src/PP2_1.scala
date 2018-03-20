

class PP2_1 
{
    var arr1 = scala.util.Random.shuffle(1 to 7)
    var arr2 = scala.util.Random.shuffle(1 to 6)
    //arr1 = Array (4,1,0,2,9,6,8,7,5,3)
    //arr2 = Array (4,6,7,2,1,0,8,3,9)
    def missing_ele
    {
      var sum1:Int = 0
      var sum2:Int = 0
      var miss_ele:Int = 0
      for(a<-arr1)
      {
        sum1 = sum1 + a
      }
      for(b<-arr2)
      {
        sum2 = sum2 + b
      }
      miss_ele = sum1 - sum2
      println("Missing element from Array2 is " +miss_ele)
    }
}

object MainObj_PP2_1
{
  def main(args:Array[String])
  {
   var x = new PP2_1
   x.missing_ele
  }
}