package io.github.avapl
package day3

@main def puzzle1(): Unit = {
  val batteryBanks = PuzzleInputParser.parsedInput
  val maxJoltages = batteryBanks.map(maxJoltageBatteries(batteryCount = 2)).map(joltage)
  val result = maxJoltages.sum
  println(result)
}
