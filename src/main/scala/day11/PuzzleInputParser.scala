package io.github.avapl
package day11

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[Device]](day = 11) {

  override protected def parse(string: String): List[Device] =
    string.splitLines.map {
      case s"$deviceId: $connectionIds" =>
        Device(deviceId, connectionIds.splitBy(" ").toSet)
    }
}