package io.github.avapl
package day12

import day12.PresentShapeElement.{EmptySpace, PresentPart}
import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[(List[Present], List[TreeRegion])](day = 12) {

  override protected def parse(string: String): (List[Present], List[TreeRegion]) =
    string.splitBlocks match {
      case presentBlocks :+ treeRegionBlock =>
        val presents = parsePresents(presentBlocks)
        val treeRegions = parseTreeRegions(treeRegionBlock)
        (presents, treeRegions)
    }

  private def parsePresents(presentBlocks: List[String]) =
    presentBlocks.map(parsePresent)

  private def parsePresent(block: String) =
    block.splitLines match {
      case s"$index:" :: presentShapeElementsLines =>
        val presentShapeElements = parsePresentShapeElements(presentShapeElementsLines)
        Present(index.toInt, presentShapeElements)
    }

  private def parsePresentShapeElements(lines: List[String]) =
    for {
      line <- lines.toVector
    } yield
      for {
        char <- line.toVector
      } yield parsePresentShapeElement(char)

  private def parsePresentShapeElement(char: Char) =
    char match {
      case '#' => PresentPart
      case '.' => EmptySpace
    }

  private def parseTreeRegions(block: String) =
    block.splitLines.map {
      case s"${width}x${height}: $presentQuantities" =>
        val presentIndexToQuantity = presentQuantities
          .splitBy(" ")
          .zipWithIndex
          .map { (quantity, index) =>
            index -> quantity.toInt
          }
          .toMap
        TreeRegion(width.toInt, height.toInt, presentIndexToQuantity)
    }
}
