package io.github.avapl
package day7

@main def puzzle2(): Unit = {
  val tachyonManifold = PuzzleInputParser.parsedInput
  val result = countTimelines(tachyonManifold)
  println(result)
}

private def countTimelines(tachyonManifold: TachyonManifold) = {
  var columnToTimelineCount = Map(tachyonManifold.startColumn -> 1L)

  (0 until tachyonManifold.rowCount).foreach { currentRow =>
    val currentRowSplitterColumns = tachyonManifold.rowToSplitterColumns.getOrElse(currentRow, Set.empty)
    columnToTimelineCount = columnToTimelineCount.toList
      .flatMap { (column, timelineCount) =>
        val newColumns =
          if (currentRowSplitterColumns.contains(column)) List(column - 1, column + 1)
          else List(column)
        newColumns.map(_ -> timelineCount)
      }
      .groupMapReduce((column, _) => column)((_, timelineCount) => timelineCount)(_ + _)
  }

  columnToTimelineCount.values.sum
}
