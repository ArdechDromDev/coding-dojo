import java.time.LocalTime

import State.State

object State extends Enumeration {
  type State = Value
  val OFF, YELLOW, RED = Value
}

case class Led(color: State) {
  def display: String = color match {
    case State.OFF => "O"
    case State.YELLOW => "Y"
    case State.RED => "R"
  }
}
case class Minutes(value: Seq[Led]) {
  def display: String = value.map(_.display).mkString
}

case class FiveMinutes(value: Seq[Led]) {
  def display: String = value.map(_.display).mkString
}

case class BerlinClock(secondState: Led, minutesState: Minutes, fiveMinutes: FiveMinutes) {
  def display: String =
    s"""
       |${secondState.display}
       |OOOO
       |OOOO
       |${fiveMinutes.display}
       |${minutesState.display}
       |""".stripMargin

}

object BerlinClock extends App {

  def display(hour: String): String = {
    val time: LocalTime = LocalTime.parse(hour)
    toClock(time).display
  }

  private def toClock(time: LocalTime): BerlinClock =
    BerlinClock(extractSeconds(time), extractMinutes(time), extractFiveMinutes(time))

  private def extractSeconds(time: LocalTime) = if (time.getSecond % 2 == 1) Led(State.YELLOW) else Led(State.OFF)

  private def extractFiveMinutes(time: LocalTime) = {
    val fiveMinutes = time.getMinute / 5
    val fiveMinutesState = (1 to 11)
      .map((value) => if (value <= fiveMinutes) Led(if (value % 3 == 0) State.RED else State.YELLOW) else Led(State.OFF))
    FiveMinutes(fiveMinutesState)
  }

  private def extractMinutes(time: LocalTime) = {
    val minute = time.getMinute
    val minutes = minute % 5
    val minuteState: Seq[Led] = (1 to 4)
      .map((value) => if (value <= minutes) Led(State.YELLOW) else Led(State.OFF))
    Minutes(minuteState)
  }
}