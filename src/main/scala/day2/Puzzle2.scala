package io.github.avapl
package day2

import scala.collection.mutable

@main def puzzle2(): Unit = {
  val idRanges = PuzzleInputParser.parsedInput
  val invalidIds = idRanges.flatMap(invalidIdsForIdRange)
  val result = invalidIds.sum
  println(result)
}

private def invalidIdsForIdRange(idRange: IdRange) = {
  val startString = idRange.start.toString
  val endString = idRange.end.toString
  val minIdLength = math.max(2, startString.length)
  val maxIdLength = endString.length
  val maxPatternLength = endString.length / 2

  val invalidIds = mutable.Set.empty[Id]
  for {
    idLength <- minIdLength to maxIdLength
    patternLength <- 1 to maxPatternLength
    if idLength % patternLength == 0
    minPattern = startString.take(patternLength)
    maxPattern = endString.take(patternLength)
    patternsCount = idLength / patternLength
    pattern <- patternsOfLength(patternLength)
    if (idLength != minIdLength || minPattern <= pattern) &&
      (idLength != maxIdLength || pattern <= maxPattern)
  } {
    val invalidId = (pattern * patternsCount).toLong
    if (idRange.contains(invalidId))
      invalidIds.add(invalidId)
  }
  invalidIds.toList
}

private def patternsOfLength(length: Int) =
  digitsCombinationsWithRepetition(length).filterNot(_.head == 0).map(_.mkString)

private def digitsCombinationsWithRepetition(length: Int) =
  List.fill(length)(0 to 9).flatten.combinations(length).flatMap(_.permutations)
