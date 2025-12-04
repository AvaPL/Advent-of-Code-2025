package io.github.avapl
package day4

type Diagram = Map[Position, DiagramElement]

sealed trait DiagramElement

object DiagramElement {
  object RollOfPaper extends DiagramElement
  object EmptySpace extends DiagramElement
}

case class Position(
    row: Int,
    column: Int
) {

  def adjacentPositions: Set[Position] = Set(
    (row - 1, column),
    (row, column + 1),
    (row + 1, column),
    (row, column - 1),
    (row - 1, column - 1),
    (row - 1, column + 1),
    (row + 1, column + 1),
    (row + 1, column - 1)
  ).map {
    case (row, column) => Position(row, column)
  }
}

val maxAdjacentRollsOfPaper = 4
