class BerlinClockTest extends org.scalatest.FunSuite {
  test("00:00:00") {
    assert(BerlinClock.display("00:00:00") ===
      """
        |O
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("00:00:01") {
    assert(BerlinClock.display("00:00:01") ===
      """
        |Y
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("00:00:15") {
    assert(BerlinClock.display("00:00:15") ===
      """
        |Y
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("00:00:02") {
    assert(BerlinClock.display("00:00:02") ===
      """
        |O
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("00:01:00") {
    assert(BerlinClock.display("00:01:00") ===
      """
        |O
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |YOOO
        |""".stripMargin)
  }

  test("00:02:00") {
    assert(BerlinClock.display("00:02:00") ===
      """
        |O
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |YYOO
        |""".stripMargin)
  }


  test("00:04:00") {
    assert(BerlinClock.display("00:04:00") ===
      """
        |O
        |OOOO
        |OOOO
        |OOOOOOOOOOO
        |YYYY
        |""".stripMargin)
  }

  test("00:06:00") {
    assert(BerlinClock.display("00:06:00") ===
      """
        |O
        |OOOO
        |OOOO
        |YOOOOOOOOOO
        |YOOO
        |""".stripMargin)
  }

  test("00:05:00") {
    assert(BerlinClock.display("00:05:00") ===
      """
        |O
        |OOOO
        |OOOO
        |YOOOOOOOOOO
        |OOOO
        |""".stripMargin)
  }

  test("00:15:00") {
    assert(BerlinClock.display("00:15:00") ===
      """
        |O
        |OOOO
        |OOOO
        |YYROOOOOOOO
        |OOOO
        |""".stripMargin)
  }


  test("00:59:00") {
    assert(BerlinClock.display("00:59:00") ===
      """
        |O
        |OOOO
        |OOOO
        |YYRYYRYYRYY
        |YYYY
        |""".stripMargin)
  }
}
