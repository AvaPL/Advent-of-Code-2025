package io.github.avapl
package day6.puzzle2

import day6.Operator.{Addition, Multiplication}
import day6.Problem
import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[Problem]](day = 6) {

  override protected def parse(string: String): List[Problem] =
    transpose(string).splitBlocks.map(parseBlock)

  /** @example
    *   Input:
    *   {{{
    *     123 328
    *      45 64
    *       6 98
    *     *   +
    *   }}}
    *   Output:
    *   {{{
    *     1  *
    *     24
    *     356
    *
    *     369+
    *     248
    *     8
    *   }}}
    */
  private def transpose(string: String) =
    string.splitLines.transpose
      .map { chars =>
        if (chars.forall(_.isWhitespace)) ""
        else chars.mkString
      }
      .mkString("\n")

  private def parseBlock(block: String) = {
    val lines = block.splitLines
    val numbers = lines.map(parseNumber)
    val operator = parseOperator(lines.head.last)
    Problem(numbers, operator)
  }

  private def parseNumber(line: String) =
    line.dropRight(1).trim.toLong

  private def parseOperator(char: Char) =
    char match {
      case '+' => Addition
      case '*' => Multiplication
    }
}
