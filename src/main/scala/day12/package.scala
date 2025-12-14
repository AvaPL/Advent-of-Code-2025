package io.github.avapl
package day12

import day12.PresentShapeElement.PresentPart

sealed trait PresentShapeElement

object PresentShapeElement {

  object PresentPart extends PresentShapeElement
  object EmptySpace extends PresentShapeElement
}

case class Present(
    index: Int,
    elements: Vector[Vector[PresentShapeElement]]
) {
  val height: Int = elements.size
  val width: Int = elements.headOption.map(_.size).getOrElse(0)
  val area: Int = elements.flatten.count(_ == PresentPart)
}

case class TreeRegion(
    width: Int,
    height: Int,
    presentIndexToQuantity: Map[Int, Int]
) {
  val area: Int = width * height
}
