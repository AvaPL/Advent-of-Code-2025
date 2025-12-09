package io.github.avapl
package day9

@main def puzzle1(): Unit = {
  val redTilesPositions = PuzzleInputParser.parsedInput
  val result = largestRectangleArea(redTilesPositions)
  println(result)
}

private def largestRectangleArea(redTilesPositions: List[RedTilePosition]) =
  redTilesPositions.combinations(2).foldLeft(0L) {
    case (maxArea, List(position1, position2)) =>
      math.max(area(position1, position2), maxArea)
  }

private def area(redTilePosition1: RedTilePosition, redTilePosition2: RedTilePosition) =
  math.abs(redTilePosition1.row - redTilePosition2.row + 1).toLong *
    math.abs(redTilePosition1.column - redTilePosition2.column + 1)
