package io.github.avapl
package day6.puzzle2

@main def puzzle2(): Unit = {
  val problems = PuzzleInputParser.parsedInput
  val problemEvaluations = problems.map(_.evaluate)
  val result = problemEvaluations.sum
  println(result)
}