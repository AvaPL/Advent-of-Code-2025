package io.github.avapl
package day4

import day4.DiagramElement.RollOfPaper

import scala.collection.mutable

@main def puzzle2(): Unit = {
  val diagram = PuzzleInputParser.parsedInput
  val result = countRollsOfPaperToRemoveWithRecursive(diagram)
  println(result)
}

private def countRollsOfPaperToRemoveWithRecursive(diagram: Diagram) = {
  val removedRollsOfPaperPositions = mutable.Set.empty[Position]

  def adjacentPositionsWithRollOfPaper(position: Position) =
    validAdjacentPositions(position).filter { adjacentPosition =>
      diagram(adjacentPosition) == RollOfPaper &&
      !removedRollsOfPaperPositions.contains(adjacentPosition)
    }

  def validAdjacentPositions(position: Position) =
    position.adjacentPositions.intersect(diagram.keySet)

  var positionsToEvaluate = diagram.collect { case (position, RollOfPaper) => position }.toSet
  while (positionsToEvaluate.nonEmpty) {
    val positionsToRemove = positionsToEvaluate.filter { position =>
      adjacentPositionsWithRollOfPaper(position).size < maxAdjacentRollsOfPaper
    }
    removedRollsOfPaperPositions.addAll(positionsToRemove)
    positionsToEvaluate = positionsToRemove.flatMap(adjacentPositionsWithRollOfPaper)
  }

  removedRollsOfPaperPositions.size
}
