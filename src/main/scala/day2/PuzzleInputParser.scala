package io.github.avapl
package day2

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[IdRange]](day = 2) {

  override protected def parse(string: String): List[IdRange] =
    string.splitBy(",").map {
      case s"$start-$end" => IdRange(start.toLong, end.toLong)
    }
}
