package io.github.avapl
package day1

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[Rotation]](day = 1) {

  override protected def parse(string: String): List[Rotation] =
    string.splitLines.map {
      case s"R$value" => value.toInt
      case s"L$value" => -value.toInt
    }
}
