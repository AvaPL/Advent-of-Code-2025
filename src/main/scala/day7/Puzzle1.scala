package io.github.avapl
package day7

@main def puzzle1(): Unit = {
  val tachyonManifold = PuzzleInputParser.parsedInput
  val result = countBeamSplits(tachyonManifold)
  println(result)
}

private def countBeamSplits(tachyonManifold: TachyonManifold) = {
  var beamSplitsCount = 0
  var currentBeamColumns = Set(tachyonManifold.startColumn)

  (0 until tachyonManifold.rowCount).foreach { currentRow =>
    val currentRowSplitterColumns = tachyonManifold.rowToSplitterColumns.getOrElse(currentRow, Set.empty)
    currentBeamColumns = currentBeamColumns.flatMap { beamColumn =>
      if (currentRowSplitterColumns.contains(beamColumn)) {
        beamSplitsCount += 1
        Set(beamColumn - 1, beamColumn + 1)
      } else Set(beamColumn)
    }
  }

  beamSplitsCount
}
