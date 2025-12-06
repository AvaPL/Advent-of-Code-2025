package io.github.avapl
package day6.puzzle1

@main def puzzle1(): Unit = {
  val problems = PuzzleInputParser.parsedInput
  val problemEvaluations = problems.map(_.evaluate)
  val result = problemEvaluations.sum
  println(result)
}