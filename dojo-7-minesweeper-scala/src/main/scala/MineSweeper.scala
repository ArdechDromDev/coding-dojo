import scala.collection.mutable

object MineSweeper {
    def solve(field: String) : String = 
    Neighborhood.extract(field).map(lines => lines.map {
        case CaseWithNeighborhood(Case('*'), _) => '*'

        case CaseWithNeighborhood(Case('.'), Neighborhood(neighborhood))=>
            neighborhood.count(_ == '*').toString().charAt(0)
        }.mkString(""))
    .mkString("\n")
}

case class Case(value: Char)
case class Neighborhood(values: Seq[Char] = Seq())
case class CaseWithNeighborhood(c: Case, neighborhood: Neighborhood)

object CaseWithNeighborhood {
    def apply(c: Char, neighborhood: Neighborhood): CaseWithNeighborhood = CaseWithNeighborhood(Case(c), neighborhood)
}


object Neighborhood {

    val emptyNeighborhood = Neighborhood()

    def extract(field: String) : Seq[Seq[CaseWithNeighborhood]] = {
        val rows = field.linesIterator.toIndexedSeq
        println(s"rows $rows")
        rows.zipWithIndex.map { case (line, y) => {
            line.zipWithIndex.map { case (c, x) => { 
                val neighbors = mutable.Buffer[Char]();

                if (x > 0 && y > 0) {
                    neighbors += rows(y-1).charAt(x-1)
                }

                if (y > 0) {
                    neighbors += rows(y-1).charAt(x)
                }

                if (x < line.length() -1 && y > 0) {
                    neighbors += rows(y-1).charAt(x+1)
                }

                if (x > 0) {
                    neighbors += line.charAt(x-1)
                }

                if (x < line.length() -1) {
                    neighbors += line.charAt(x+1)
                }

                if (x > 0 && y < rows.size - 1) {
                    neighbors += rows(y+1).charAt(x-1)
                }


                if (y < rows.size - 1) {
                    neighbors += rows(y+1).charAt(x)
                }

                if (x < line.length() -1 && y < rows.size - 1)  {
                    neighbors += rows(y+1).charAt(x+1)
                }




                CaseWithNeighborhood(c, Neighborhood(neighbors.toSeq))
            }}
        }}.toSeq
    }
}

object MineSweeper2 {
    def solve(input: String): String = Input.field(input).map {
        case (Input.Mine, _) => Output.Mine
        case (_, neighbors) => Output.Count(neighbors.count(_ == Input.Mine))
    }.render()
}

object Input {

    def field(input: String): Field =
        new Field(
            input.linesIterator.map {
                line => line.map { toCell _ }.toIndexedSeq
            }.toIndexedSeq)

    private def toCell(input: Char): Cell = input match {
        case '*' => Mine
        case '.' => Empty
    }

    class Field(private val matrix: IndexedSeq[IndexedSeq[Cell]]) {
        private case class Coordinates(x: Int, y: Int)

        def map(f: (Cell, Seq[Cell]) => Output.Cell): Output.Field =
            new Output.Field(
                matrix.zipWithIndex.map {
                    case (line, y) => line.zipWithIndex.map {
                        case (cell, x) => f(cell, neighbors(Coordinates(x, y)))
                    }
                })
            

        private def neighbors(coordinates: Coordinates): Seq[Cell] =
            for {
                neighbor <- neighborsCoordinates(coordinates)
                cell <- cellAt(neighbor)
            } yield (cell)

        private def neighborsCoordinates(coordinates: Coordinates): Seq[Coordinates] =
            (for {
                a <- (coordinates.x - 1).to(coordinates.x + 1)
                b <- (coordinates.y - 1).to(coordinates.y + 1)
            } yield Coordinates(a, b))
            .filter(_ != coordinates)

        private def cellAt(coordinates: Coordinates): Option[Cell] =
            for {
                line <- line(coordinates.y)
                cell <- cell(line, coordinates.x)
            } yield cell

        private def line(y: Int): Option[IndexedSeq[Cell]] = 
            if (matrix.indices.contains(y))
                Some(matrix(y))
            else
                None

        private def cell(line: IndexedSeq[Cell], x: Int): Option[Cell] =
            if (line.indices.contains(x))
                Some(line(x))
            else 
                None
    }

    sealed trait Cell

    case object Mine extends Cell
    case object Empty extends Cell
}

object Output {
    
    sealed trait Cell
    case object Mine extends Cell
    case class Count(value: Int) extends Cell

    class Field(private val matrix: IndexedSeq[IndexedSeq[Cell]]) {
        def render(): String = 
        matrix.map{ 
            line => line.map {
                case Mine => '*'
                case Count(c) => c.toString()(0)
            }.mkString("")
        }.mkString("\n")
    }

}



