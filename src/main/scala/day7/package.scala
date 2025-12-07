package io.github.avapl
package day7

type Row = Int
type Column = Int

case class TachyonManifold(
    startColumn: Column,
    rowCount: Int,
    rowToSplitterColumns: Map[Row, Set[Column]]
)
