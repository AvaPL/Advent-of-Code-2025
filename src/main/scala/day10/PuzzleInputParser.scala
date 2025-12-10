package io.github.avapl
package day10

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[Machine]](day = 10) {

  override protected def parse(string: String): List[Machine] =
    string.splitLines.map(parseMachine)

  private def parseMachine(line: String) =
    line match {
      case s"[$indicatorLightsStatesString] $buttonsString {$joltageRequirementsString}" =>
        val indicatorLightStates = parseIndicatorLightsStates(indicatorLightsStatesString)
        val joltageRequirements = parseJoltageRequirements(joltageRequirementsString)
        val indicatorLights = indicatorLightStates.zip(joltageRequirements).map {
          (indicatorLightState, joltageRequirement) => IndicatorLight(indicatorLightState, joltageRequirement)
        }.toList
        val machineState = MachineState(indicatorLights)
        val buttons = parseButtons(buttonsString)
        Machine(machineState, buttons)
    }

  private def parseIndicatorLightsStates(string: String) =
    string.map {
      case '#' => IndicatorLightState.On
      case '.' => IndicatorLightState.Off
    }

  private def parseJoltageRequirements(string: String) =
    string.map(_.asDigit)

  private def parseButtons(string: String) =
    string.splitBy(" ").map {
      case s"($indicesString)" =>
        val indicatorLightIndices = indicesString.splitBy(",").map(_.toInt).toSet
        Button(indicatorLightIndices)
    }.toSet
}
