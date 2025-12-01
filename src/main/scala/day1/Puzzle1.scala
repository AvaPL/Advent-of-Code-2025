package io.github.avapl
package day1

@main def puzzle1(): Unit = {
  val rotations = PuzzleInputParser.parsedInput
  val result = determinePassword(rotations)
  println(result)
}

private def determinePassword(rotations: List[Rotation]) = {
  var currentDialValue = 50
  var password = 0
  rotations.foreach { rotation =>
    currentDialValue = math.floorMod(currentDialValue + rotation, dialNumbersCount)
    if (currentDialValue == 0)
      password += 1
  }
  password
}
