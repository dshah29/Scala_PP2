

object PP2_2 
{
  def main(args:Array[String])
  {
    println("{{{}}}"+balance("{{{}}}".toList))
    println("[{()}]"+balance("[{()}]".toList))
    println("(()"+balance("(()".toList))    
  }
  def balance(chars:List[Char]):Boolean=
  {
    def isbalance(chars:List[Char], open:Int): Boolean =
    {
      if(chars.isEmpty)
      {
          open == 0
      }
      else if(chars.head == '(')
      {
        isbalance(chars.tail,open +1)
      }
      else if(chars.head == ')')
      {
        open > 0 && isbalance(chars.tail , open-1 )
      }
      else if(chars.head == '{')
      {
        isbalance(chars.tail,open +1)
      }
      else if(chars.head == '}')
      {
        open > 0 && isbalance(chars.tail , open-1 )
      }else if(chars.head == '[')
      {
        isbalance(chars.tail,open +1)
      }
      else if(chars.head == ']')
      {
        open > 0 && isbalance(chars.tail , open-1 )
      }
      else
      {
        isbalance(chars.tail, open)
      }
    }
  isbalance(chars, 0)
  }
}