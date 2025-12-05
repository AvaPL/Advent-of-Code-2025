package io.github.avapl
package day5

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[(List[FreshIdRange], List[Id])](day = 5) {

  override protected def parse(string: String): (List[FreshIdRange], List[Id]) = {
    val List(freshIdRangesBlock, idsBlock) = string.splitBlocks
    (parseFreshIdRanges(freshIdRangesBlock), parseIds(idsBlock))
  }

  private def parseFreshIdRanges(block: String) =
    block.splitLines.map {
      case s"$start-$end" => FreshIdRange(start.toLong, end.toLong)
    }

  private def parseIds(block: String) =
    block.splitLines.map(_.toLong)
}
