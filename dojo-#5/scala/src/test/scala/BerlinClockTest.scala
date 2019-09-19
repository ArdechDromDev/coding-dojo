class BerlinClockTest extends org.scalatest.FunSuite {
  test("00:00:00") {
    assert(BerlinClock.display("00:00:00") ===
      """
        |Y
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("00:00:01") {
    assert(BerlinClock.display("00:00:01") ===
      """
        |O
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("00:00:15") {
    assert(BerlinClock.display("00:00:15") ===
      """
        |O
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("00:00:02") {
    assert(BerlinClock.display("00:00:02") ===
      """
        |Y
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("00:01:00") {
    assert(BerlinClock.display("00:01:00") ===
      """
        |Y
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |YOOO
        |""".stripMargin)
  }

  test("00:02:00") {
    assert(BerlinClock.display("00:02:00") ===
      """
        |Y
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |YYOO
        |""".stripMargin)
  }


  test("00:04:00") {
    assert(BerlinClock.display("00:04:00") ===
      """
        |Y
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |YYYY
        |""".stripMargin)
  }

  test("00:06:00") {
    assert(BerlinClock.display("00:06:00") ===
      """
        |Y
        |OOOO
        |OOOO
        |YOOOOOOOOOO
        |YOOO
        |""".stripMargin)
  }

  test("00:05:00") {
    assert(BerlinClock.display("00:05:00") ===
      """
        |Y
        |OOOO
        |OOOO
        |YOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("00:15:00") {
    assert(BerlinClock.display("00:15:00") ===
      """
        |Y
        |OOOO
        |OOOO
        |YYROOOOOOOO
        |OOOO
        |""".stripMargin)
  }


  test("00:59:00") {
    assert(BerlinClock.display("00:59:00") ===
      """
        |Y
        |OOOO
        |OOOO
        |YYRYYRYYRYY
        |YYYY
        |""".stripMargin)
  }

  test("01:00:00") {
    assert(BerlinClock.display("01:00:00") ===
      """
        |Y
        |OOOO
        |ROOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("02:00:00") {
    assert(BerlinClock.display("02:00:00") ===
      """
        |Y
        |OOOO
        |RROO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("04:00:00") {
    assert(BerlinClock.display("04:00:00") ===
      """
        |Y
        |OOOO
        |RRRR
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("05:00:00") {
    assert(BerlinClock.display("05:00:00") ===
      """
        |Y
        |ROOO
        |OOOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("06:00:00") {
    assert(BerlinClock.display("06:00:00") ===
      """
        |Y
        |ROOO
        |ROOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("10:00:00") {
    assert(BerlinClock.display("10:00:00") ===
      """
        |Y
        |RROO
        |OOOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("20:00:00") {
    assert(BerlinClock.display("20:00:00") ===
      """
        |Y
        |RRRR
        |OOOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("23:59:59") {
    assert(BerlinClock.display("23:59:59") ===
      """
        |O
        |RRRR
        |RRRO
        |YYRYYRYYRYY
        |YYYY
        |""".stripMargin)
  }

  test("12:56:01") {
    assert(BerlinClock.display("12:56:01") ===
      """
        |O
        |RROO
        |RROO
        |YYRYYRYYRYY
        |YOOO
        |""".stripMargin)
  }
}
