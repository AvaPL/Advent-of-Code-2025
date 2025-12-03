package io.github.avapl
package day3

case class Battery(joltage: Long)

case class BatteryBank(batteries: Vector[Battery])

def maxJoltageBatteries(batteryCount: Int)(batteryBank: BatteryBank): Vector[Battery] = {

  def loop(batteryCount: Int, batteryBank: BatteryBank): Vector[Battery] =
    if (batteryCount <= 0) Vector.empty
    else {
      val battery1Index = maxBatteryIndex(batteryBank)
      val newBatteryCount = batteryCount - 1
      val rightSubBank = BatteryBank(batteryBank.batteries.drop(battery1Index + 1))
      val rightBatteryCount = math.min(batteryBank.batteries.size - (battery1Index + 1), newBatteryCount)
      val leftSubBank = BatteryBank(batteryBank.batteries.take(battery1Index))
      val leftBatteryCount = newBatteryCount - rightBatteryCount
      loop(leftBatteryCount, leftSubBank) :++ batteryBank.batteries(battery1Index) +: loop(rightBatteryCount, rightSubBank)
    }

  loop(batteryCount, batteryBank)
}

private def maxBatteryIndex(batteryBank: BatteryBank) = {
  val maxBattery = batteryBank.batteries.maxBy(_.joltage)
  batteryBank.batteries.indexOf(maxBattery)
}

def joltage(batteries: Vector[Battery]): Long =
  batteries.map(_.joltage).mkString.toLong
