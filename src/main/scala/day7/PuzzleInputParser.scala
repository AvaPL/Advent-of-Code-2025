package io.github.avapl
package day7

import util.InputParser
import util.StringOps.*

import scala.collection.mutable

object PuzzleInputParser extends InputParser[TachyonManifold](day = 7) {

  override protected def parse(string: String): TachyonManifold = {
    var startColumn = Option.empty[Column]
    var rowCount = 0
    val rowToSplitterColumns = mutable.Map.empty[Row, Set[Column]]

    string.splitLines.zipWithIndex.foreach { (line, row) =>
      line.zipWithIndex.foreach { (char, column) =>
        char match {
          case '.' =>
          case '^' =>
            rowToSplitterColumns.updateWith(row) { splitterColumns =>
              Some(splitterColumns.getOrElse(Set.empty) + column)
            }
          case 'S' =>
            startColumn = Some(column)
        }
      }
      rowCount += 1
    }

    TachyonManifold(
      startColumn.get,
      rowCount,
      rowToSplitterColumns.toMap
    )
  }
}
