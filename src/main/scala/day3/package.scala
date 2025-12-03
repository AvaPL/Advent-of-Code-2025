package io.github.avapl
package day3

case class Battery(joltage: Long)

case class BatteryBank(batteries: Vector[Battery])

def maxJoltageBatteries(batteryCount: Int)(batteryBank: BatteryBank): Vector[Battery] =
  if (batteryCount <= 0) Vector.empty
  else {
    val selectedBatteryIndex = maxBatteryIndex(batteryBank)
    val newBatteryCount = batteryCount - 1
    val rightSubBank = BatteryBank(batteryBank.batteries.drop(selectedBatteryIndex + 1))
    val rightBatteryCount = math.min(batteryBank.batteries.size - (selectedBatteryIndex + 1), newBatteryCount)
    val leftSubBank = BatteryBank(batteryBank.batteries.take(selectedBatteryIndex))
    val leftBatteryCount = newBatteryCount - rightBatteryCount

    maxJoltageBatteries(leftBatteryCount)(leftSubBank) :++ batteryBank.batteries(selectedBatteryIndex) +: maxJoltageBatteries(rightBatteryCount)(rightSubBank)
  }

private def maxBatteryIndex(batteryBank: BatteryBank) = {
  val maxBattery = batteryBank.batteries.maxBy(_.joltage)
  batteryBank.batteries.indexOf(maxBattery)
}

def joltage(batteries: Vector[Battery]): Long =
  batteries.map(_.joltage).mkString.toLong
