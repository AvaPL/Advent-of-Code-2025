package io.github.avapl
package day10.puzzle1

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[Machine]](day = 10) {

  override protected def parse(string: String): List[Machine] =
    string.splitLines.map(parseMachine)

  private def parseMachine(line: String) =
    line match {
      case s"[$indicatorLightsStatesString] $buttonsString {$_}" =>
        val indicatorLightStates = parseIndicatorLightsStates(indicatorLightsStatesString)
        val buttons = parseButtons(buttonsString)
        Machine(indicatorLightStates, buttons)
    }

  private def parseIndicatorLightsStates(string: String) =
    string.map {
      case '#' => IndicatorLightState.On
      case '.' => IndicatorLightState.Off
    }.toList

  private def parseButtons(string: String) =
    string.splitBy(" ").map {
      case s"($indicesString)" =>
        val indicatorLightIndices = indicesString.splitBy(",").map(_.toInt).toSet
        Button(indicatorLightIndices)
    }.toSet
}
