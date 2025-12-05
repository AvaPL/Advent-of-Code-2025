package io.github.avapl
package day5

@main def puzzle2(): Unit = {
  val (freshIdRanges, _) = PuzzleInputParser.parsedInput
  val result = compactFreshIdRanges(freshIdRanges).map(_.length).sum
  println(result)
}

private def compactFreshIdRanges(freshIdRanges: List[FreshIdRange]) =
  freshIdRanges
    .sortBy(_.start)
    .foldLeft(Vector.empty[FreshIdRange]) {
      case (init :+ last, range) if range.start <= last.end =>
        init :+ FreshIdRange(last.start, math.max(last.end, range.end))
      case (acc, range) =>
        acc :+ range
    }
