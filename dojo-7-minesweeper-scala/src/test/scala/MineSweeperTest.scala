import org.scalatest.FunSpec

/*
*...
....
.*..
....
*/

/*
*100
2210
1*10
1110
*/

class MineSweeperTest extends FunSpec {
    it("* should return *") {
        assert(MineSweeper.solve("*") == "*")
    }

    it(". should return 0") {
        assert(MineSweeper.solve(".") == "0")
    }

    it("empty string should return empty string") {
        assert(MineSweeper.solve("") == "")
    }

    it("** should return **") {
        assert(MineSweeper.solve("**") == "**")
    }

    it(".. should return 00") {
        assert(MineSweeper.solve("..") == "00")
    }

    it("*. should return *1") {
        assert(MineSweeper.solve("*.") == "*1")
    } 

    it ("""
        *...
        ....
        .*..
        ....
        
        should return

        *100
        2210
        1*10
        1110
        """) {
        assert(MineSweeper2.solve(
        """*...
        |....
        |.*..
        |....""".stripMargin('|')) ==
        """*100
        |2210
        |1*10
        |1110""".stripMargin('|'))
        }
}

class NeighborhoodTest extends FunSpec {
    it ("empty string should return empty string") {
        assert(Neighborhood.extract("") == Seq.empty)
    }

    it (". should return case without neighbors") {
        assert(Neighborhood.extract(".") == Seq(Seq(CaseWithNeighborhood('.', Neighborhood(Seq.empty)))))
    }

    it ("12 should return case with 1 neighbors") {
        assert(Neighborhood.extract("12") == Seq(Seq(
            CaseWithNeighborhood('1', Neighborhood(Seq('2'))),
            CaseWithNeighborhood('2', Neighborhood(Seq('1')))
            )))
    }

    it ("123 should return case with 2 neighbors") {
        assert(Neighborhood.extract("123") == Seq(Seq(
            CaseWithNeighborhood('1', Neighborhood(Seq('2'))),
            CaseWithNeighborhood('2', Neighborhood(Seq('1', '3'))),
            CaseWithNeighborhood('3', Neighborhood(Seq('2')))
            )))
    }

   it ("1\n2 should return case with 2 lines neighbors") {
        assert(Neighborhood.extract(
            """1
            |2""".stripMargin('|')) == Seq(
            Seq(CaseWithNeighborhood('1', Neighborhood(Seq('2')))),
            Seq(CaseWithNeighborhood('2', Neighborhood(Seq('1'))))
        ))
    }

   /*it ("123\n456\n789 should return case with 3 lines with 3 neighbors") {
        assert(Neighborhood.extract(
            """123
            |456
            |789""".stripMargin('|')) == Seq(
            Seq(CaseWithNeighborhood('1', Neighborhood(Seq('2', '4', '5')))),
            Seq(CaseWithNeighborhood('2', Neighborhood(Seq('1', '3', '4', '5', '6')))),
            Seq(CaseWithNeighborhood('3', Neighborhood(Seq('2', '5', '6')))),
            Seq(CaseWithNeighborhood('4', Neighborhood(Seq('1', '2', '5', '7', '8')))),
            Seq(CaseWithNeighborhood('5', Neighborhood(Seq('1', '2', '3', '4', '6', '7', '8', '9')))),
            Seq(CaseWithNeighborhood('6', Neighborhood(Seq('2', '3', '5', '8', '9')))),
            Seq(CaseWithNeighborhood('7', Neighborhood(Seq('4', '5', '8')))),
            Seq(CaseWithNeighborhood('8', Neighborhood(Seq('4', '5', '6', '7', '9')))),
            Seq(CaseWithNeighborhood('9', Neighborhood(Seq('5', '6', '8')))),            
        ))

    }*/

}




