package io.github.avapl
package day6.puzzle1

import day6.Operator.{Addition, Multiplication}
import day6.Problem
import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[Problem]](day = 6) {

  override protected def parse(string: String): List[Problem] =
    string.splitLines.map(_.trim.splitByRegex("\\s+")).transpose.map {
      case init :+ last =>
        val numbers = init.map(_.toLong)
        val operator = parseOperator(last)
        Problem(numbers, operator)
    }

  private def parseOperator(string: String) =
    string match {
      case "+" => Addition
      case "*" => Multiplication
    }
}
