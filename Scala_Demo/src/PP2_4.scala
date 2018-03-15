import scala.io.Source

object PP2_4 
{
  def countW(file:String)
  {
    val src =Source.fromFile(file)
    val count=
      (for
          {
            line <- src.getLines()
          }yield
          {
            val words = line.split("\\s+")
            words.size
          }).sum
       println(count)
   }
  def main(args:Array[String])
  {
    countW("/opt/eclipse/abc")
  }
}