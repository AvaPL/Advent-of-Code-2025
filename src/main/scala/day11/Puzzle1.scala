package io.github.avapl
package day11

@main def puzzle1(): Unit = {
  val devices = PuzzleInputParser.parsedInput
  val result = countPaths(devices, sourceDeviceId = "you", targetDeviceId = "out")
  println(result)
}
