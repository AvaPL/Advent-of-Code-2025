package io.github.avapl
package day4

import day4.DiagramElement.RollOfPaper

@main def puzzle1(): Unit = {
  val diagram = PuzzleInputParser.parsedInput
  val result = countRollsOfPaperToRemove(diagram)
  println(result)
}

private def countRollsOfPaperToRemove(diagram: Diagram) =
  diagram.count {
    case (position, RollOfPaper) =>
      val adjacentRollsOfPaperCount = position.adjacentPositions.count { adjacentPosition =>
        diagram.get(adjacentPosition).contains(RollOfPaper)
      }
      adjacentRollsOfPaperCount < maxAdjacentRollsOfPaper
    case _ => false
  }
