package io.github.avapl
package day5

@main def puzzle1(): Unit = {
  val (freshIdRanges, ids) = PuzzleInputParser.parsedInput
  val result = ids.count(id => freshIdRanges.exists(_.contains(id)))
  println(result)
}