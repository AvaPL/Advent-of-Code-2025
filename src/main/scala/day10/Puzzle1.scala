package io.github.avapl
package day10

import scala.collection.mutable

@main def puzzle1(): Unit = {
  val machines = PuzzleInputParser.parsedInput
  val fewestButtonPresses = machines.map(countFewestButtonPresses)
  val result = fewestButtonPresses.sum
  println(result)
}

private def countFewestButtonPresses(machine: Machine) = {
  val initialMachineState = machine.state.off
  val targetMachineState = machine.state
  val machineStateToFewestButtonPresses = mutable.Map.empty[MachineState, Int]

  def loop(currentMachineState: MachineState, button: Button, buttonPressCount: Int = 0): Unit = {
    val newButtonPressCount = buttonPressCount + 1
    if (machineStateToFewestButtonPresses.get(targetMachineState).exists(_ <= newButtonPressCount))
      () // target already reached in same amount or fewer steps
    else {
      val newMachineState = currentMachineState.push(button)
      machineStateToFewestButtonPresses.get(newMachineState) match {
        case Some(fewestButtonPresses) if fewestButtonPresses <= newButtonPressCount =>
          () // current state can be reached in same amount or fewer steps
        case _ =>
          machineStateToFewestButtonPresses.put(newMachineState, newButtonPressCount)
          (machine.buttons - button).foreach { nextButton =>
            loop(newMachineState, nextButton, newButtonPressCount)
          }
      }
    }
  }

  machine.buttons.foreach(loop(initialMachineState, _))
  machineStateToFewestButtonPresses(targetMachineState)
}
