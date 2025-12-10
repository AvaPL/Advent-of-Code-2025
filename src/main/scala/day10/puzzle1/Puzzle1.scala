package io.github.avapl
package day10.puzzle1

import day10.Button

import scala.collection.mutable

@main def puzzle1(): Unit = {
  val machines = PuzzleInputParser.parsedInput
  val fewestButtonPresses = machines.map(countFewestButtonPresses)
  val result = fewestButtonPresses.sum
  println(result)
}

private def countFewestButtonPresses(machine: Machine) = {
  val targetState = machine.indicatorLights
  val initialState = targetState.map(_ => IndicatorLightState.Off)
  val stateToFewestButtonPresses = mutable.Map.empty[List[IndicatorLightState], Int]

  def loop(currentState: List[IndicatorLightState], button: Button, buttonPressCount: Int = 0): Unit = {
    val newButtonPressCount = buttonPressCount + 1
    if (stateToFewestButtonPresses.get(targetState).exists(_ <= newButtonPressCount))
      () // target already reached in same amount or fewer steps
    else {
      val newState = pushButton(currentState, button)
      stateToFewestButtonPresses.get(newState) match {
        case Some(fewestButtonPresses) if fewestButtonPresses <= newButtonPressCount =>
          () // current state can be reached in same amount or fewer steps
        case _ =>
          stateToFewestButtonPresses.put(newState, newButtonPressCount)
          (machine.buttons - button).foreach { nextButton =>
            loop(newState, nextButton, newButtonPressCount)
          }
      }
    }
  }

  machine.buttons.foreach(loop(initialState, _))
  stateToFewestButtonPresses(targetState)
}

private def pushButton(indicatorLights: List[IndicatorLightState], button: Button) =
  indicatorLights.zipWithIndex.map {
    case (indicatorLight, index) if button.indices.contains(index) => indicatorLight.toggle
    case (indicatorLight, _)                                       => indicatorLight
  }
