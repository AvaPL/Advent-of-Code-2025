package io.github.avapl
package day1

@main def puzzle2(): Unit = {
  val rotations = PuzzleInputParser.parsedInput
  val result = determinePasswordMethod0x434C49434B(rotations)
  println(result)
}

private def determinePasswordMethod0x434C49434B(rotations: List[Rotation]) = {
  var currentDialValue = 50
  var password = 0
  rotations.foreach { rotation =>
    val fullRotationsCount = math.abs(rotation) / dialNumbersCount
    val normalizedRotation = rotation % dialNumbersCount
    val newDialValue = math.floorMod(currentDialValue + normalizedRotation, dialNumbersCount)
    password += fullRotationsCount
    if (
      newDialValue == 0 || // points at 0
      currentDialValue != 0 && currentDialValue + normalizedRotation != newDialValue // crosses 0
    ) password += 1
    currentDialValue = newDialValue
  }
  password
}
