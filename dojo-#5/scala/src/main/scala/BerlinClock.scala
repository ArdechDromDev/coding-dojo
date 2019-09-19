import java.time.LocalTime

sealed trait LedState
case object On extends LedState
case object Off extends LedState

case class Led(state: LedState)
case class Minutes(leds: Seq[Led])
case class FiveMinutes(leds: Seq[Led])
case class Hours(leds: Seq[Led])
case class FiveHours(leds: Seq[Led])
case class BerlinClock(secondState: Led, minutesState: Minutes, fiveMinutes: FiveMinutes, hours: Hours, fiveHours: FiveHours)

object ConsoleDisplay {
  val off = "O"
  val red = "R"
  val yellow = "Y"

  def display(berlinClock: BerlinClock): String =
    Seq(
        display(berlinClock.secondState),
        display(berlinClock.fiveHours),
        display(berlinClock.hours),
        display(berlinClock.fiveMinutes),
        display(berlinClock.minutesState))
      .mkString(sep = "\n", start = "\n", end = "\n")

  private def display(led: Led) =
    led.state match {
      case On => yellow
      case Off => off
    }

  private def display(minutes: Minutes) = displayMonochromeLedStripe(yellow)(minutes.leds)
  private def display(hours: Hours) = displayMonochromeLedStripe(red)(hours.leds)
  private def display(fiveHours: FiveHours) = displayMonochromeLedStripe(red)(fiveHours.leds)

  private def displayMonochromeLedStripe(color: String)(leds: Seq[Led]) =
    leds.map(_.state)
      .map({
        case On => color
        case Off => off
      }).mkString

  private def display(fiveMinutes: FiveMinutes) =
    fiveMinutes.leds
      .zipWithIndex
      .map({ case (led, index) => (led.state, index % 3 == 2) })
      .map({
        case (Off, _) => off
        case (On, true) => red
        case (On, false) => yellow
    }).mkString
}

object BerlinClock extends App {

  def display(hour: String): String = {
    val time: LocalTime = LocalTime.parse(hour)
    ConsoleDisplay.display(toClock(time))
  }

  private def toClock(time: LocalTime): BerlinClock =
    BerlinClock(
      extractSeconds(time),
      extractMinutes(time),
      extractFiveMinutes(time),
      extractHours(time),
      extractFiveHours(time))

  private def extractSeconds(time: LocalTime) =
    Led(if (time.getSecond % 2 == 0) On else Off)

  private def extractFiveMinutes(time: LocalTime) = {
    val fiveMinutes = time.getMinute / 5
    val fiveMinutesLeds = (1 to 11)
      .map(value => if (value <= fiveMinutes) On else Off)
      .map(Led)
    FiveMinutes(fiveMinutesLeds)
  }

  private def extractMinutes(time: LocalTime) = {
    val minutes = time.getMinute % 5
    val minuteLeds: Seq[Led] = (1 to 4)
      .map(value => if (value <= minutes) On else Off)
      .map(Led)
    Minutes(minuteLeds)
  }

  private def extractHours(time: LocalTime) = {
    val hours = time.getHour % 5
    val leds: Seq[Led] = (1 to 4)
      .map(value => if (value <= hours) On else Off)
      .map(Led)
    Hours(leds)
  }

  private def extractFiveHours(time: LocalTime) = {
    val fiveHours = time.getHour / 5
    val leds: Seq[Led] = (1 to 4)
      .map(value => if (value <= fiveHours) On else Off)
      .map(Led)
    FiveHours(leds)
  }

}