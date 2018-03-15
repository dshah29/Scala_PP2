object PP2_3 
{
  def permutations(s: String): List[String] = 
  {   
    def merge(x: String, c: Char): Seq[String] =
      
      for (i <- 0 to x.length) yield
        x.substring(0, i) + c + x.substring(i, x.length)

      if (s.length() == 1)
        List(s)
      else
        permutations(s.substring(0, s.length - 1)).flatMap 
        { p =>
          merge(p, s.charAt(s.length - 1))
        }
  }
  def main(args:Array[String])
  {
    println(permutations("1234"))
  }
}