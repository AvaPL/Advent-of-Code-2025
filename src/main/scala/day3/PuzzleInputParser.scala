package io.github.avapl
package day3

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[BatteryBank]](day = 3) {

  override protected def parse(string: String): List[BatteryBank] =
    string.splitLines.map(parseBatteryBank)

  private def parseBatteryBank(batteryBankString: String) =
    BatteryBank(batteryBankString.map(parseBattery).toVector)

  private def parseBattery(batteryChar: Char) =
    Battery(batteryChar.asDigit)
}
