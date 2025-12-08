package io.github.avapl
package day8

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[Position]](day = 8) {

  override protected def parse(string: String): List[Position] =
    string.splitLines.map {
      case s"$x,$y,$z" => Position(x.toInt, y.toInt, z.toInt)
    }
}
