import util.Random._
import javax.swing.BoxLayout
import javax.swing.JFrame
import javax.swing.JButton
import javax.swing.GroupLayout
import javax.swing.JToggleButton
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JLabel
import javax.swing.JTextArea
import java.awt.Font
import javax.swing.JTextPane
import java.awt.Color
import java.awt.Button

//import scala.collection.parallel.mutable.ParTrieMap.Size

class RandomDemo {
  var R = scala.util.Random.shuffle(1 to 7)
  val LST = List(R.seq(0), R.seq(1), R.seq(2)).sorted.toList
  println(LST.toString())

  def Random_num_generator: List[Int] =
    {
      var count: Int = 0
      var r = scala.util.Random.shuffle(1 to 7)
      val lst = List(r.seq(0), r.seq(1), r.seq(2))
      for (i <- 0 to 2) {
        if (LST.contains(r.seq(i))) count = count + 1
        if (count == 3) return Random_num_generator
      }
      return lst
    }

  def check(lst: List[Int]): Int =
    {
      var result = 0
      for (n <- 0 to 2) {
        if (lst.contains(LST(n))) result += 1
      }
      return result
    }
}

class UI extends JFrame with ActionListener {

  setTitle("Three of a Crime")
  setBounds(200, 200, 500, 300)
  setResizable(false)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  setLayout(null)

  var rd = new RandomDemo

  val instructions = "There are 7 people being accused of a crime. You must choose which 3 of   them are guilty. " +
    "Only three can be viewed at a time, but you will be told howmany of the ones being shown are guilty. Clicking " +
    "'New Lineup' will show a new set of 3 people. When you want to make a guess, select 3 buttons and click 'Check'. " +
    "But be careful, a wrong guess means you lose!"
  val winText = "Congratulations! You found all of the perpetrators!\n Click 'New Game' to play again"
  def loseText(numCorrect: Int): String = "Oh no! " + (3 - numCorrect).toString() + " of the perpetrators got away. " +
    "\n Click 'New Game' to play again"
  val instrArea = new JTextArea(instructions)
  instrArea.setLineWrap(true)
  instrArea.setEditable(false)
  instrArea.setBounds(10, 10, 480, 100)
  add(instrArea)

  val buttons = Array(
    new JToggleButton("1"),
    new JToggleButton("2"),
    new JToggleButton("3"),
    new JToggleButton("4"),
    new JToggleButton("5"),
    new JToggleButton("6"),
    new JToggleButton("7"))
  val checkButton = new JButton("Check")
  checkButton.setActionCommand("check")
  checkButton.setBounds(190, 230, 100, 40)
  checkButton.addActionListener(this)
  checkButton.setEnabled(false)
  add(checkButton)
  val newGameButton = new JButton("New Game")
  newGameButton.setActionCommand("game")
  newGameButton.setBounds(190, 230, 100, 40)
  newGameButton.addActionListener(this)
  newGameButton.setVisible(false)
  add(newGameButton)

  val visablePeople = Array(new JTextPane(), new JTextPane(), new JTextPane())
  var taX = 40
  for (ta <- visablePeople) {
    ta.setBounds(taX, 140, 40, 40)
    ta.setEditable(false)
    ta.setFont(ta.getFont.deriveFont(30f))
    taX += 80
    add(ta)
  }

  val numGuiltyLabel = new JLabel("Guilty number")
  numGuiltyLabel.setBounds(270, 110, 100, 30)
  add(numGuiltyLabel)
  val numGuiltyArea = new JTextPane()
  numGuiltyArea.setBounds(290, 140, 40, 40)
  numGuiltyArea.setEditable(false)
  numGuiltyArea.setBackground(new Color(255, 200, 200))
  numGuiltyArea.setFont(numGuiltyArea.getFont.deriveFont(30f))
  add(numGuiltyArea)

  val newLineupButton = new JButton("New lineup")
  newLineupButton.setBounds(370, 140, 100, 40)
  newLineupButton.setActionCommand("lineup")
  newLineupButton.addActionListener(this)
  add(newLineupButton)

  var btnX = 0
  for (b <- buttons) {
    b.setBounds(btnX, 200, 80, 25);
    b.setActionCommand(b.getText)
    b.addActionListener(this)
    add(b)
    btnX += 70
  }

  newLineup

  def newLineup {
    var lineup = rd.Random_num_generator
    for (n <- 0 to 2) {
      visablePeople(n).setText(lineup(n).toString)
    }
    numGuiltyArea.setText(rd.check(lineup).toString)
    println(lineup)
  }

  @Override
  def actionPerformed(e: ActionEvent) {
    if ("check".equals(e.getActionCommand)) {
      var arr = new Array[Int](3)
      var index = 0
      for (n <- 1 to 7) {
        if (buttons(n - 1).isSelected()) {
          arr(index) = n
          index += 1
        }
      }
      var numCorrect = rd.check(arr.toList)
      if (numCorrect == 3) {
        //WIN
        instrArea.setText(winText)
      } else {
        //LOSE
        instrArea.setText(loseText(numCorrect))
      }
      checkButton.setVisible(false)
      newGameButton.setVisible(true)
    } else if ("1234567".contains(e.getActionCommand)) {
      var numToggled = 0
      for (b <- buttons) {
        if (b.isSelected()) numToggled += 1
      }
      if (numToggled > 2) {
        for (b <- buttons) {
          if (!b.isSelected()) b.setEnabled(false)
        }
        checkButton.setEnabled(true)
      } else {
        for (b <- buttons) {
          b.setEnabled(true)
        }
        checkButton.setEnabled(false)
      }
    } else if ("lineup".equals(e.getActionCommand)) {
      newLineup
    } else if ("game".equals(e.getActionCommand)) {
      checkButton.setVisible(true)
      newGameButton.setVisible(false)
      for (b <- buttons) {
        b.setSelected(false)
        b.setEnabled(true)
      }
      instrArea.setText(instructions)
      rd = new RandomDemo
      newLineup
    }
  }
}

object PP2_5 {
  def main(args: Array[String]) {
    val ui = new UI
    ui.setVisible(true)
  }
}
