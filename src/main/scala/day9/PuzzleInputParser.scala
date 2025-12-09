package io.github.avapl
package day9

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[RedTilePosition]](day = 9) {

  override protected def parse(string: String): List[RedTilePosition] =
    string.splitLines.map {
      case s"$row,$column" => RedTilePosition(row.toInt, column.toInt)
    }
}
