package io.github.avapl
package day4

import day4.DiagramElement.{EmptySpace, RollOfPaper}
import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[Diagram](day = 4) {

  override protected def parse(string: String): Diagram = {
    for {
      (line, row) <- string.splitLines.zipWithIndex
      (char, column) <- line.zipWithIndex
    } yield {
      val diagramElement = char match {
        case '@' => RollOfPaper
        case '.' => EmptySpace
      }
      Position(row, column) -> diagramElement
    }
  }.toMap
}
