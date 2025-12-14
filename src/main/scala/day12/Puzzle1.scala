package io.github.avapl
package day12

@main def puzzle1(): Unit = {
  val (presents, treeRegions) = PuzzleInputParser.parsedInput
  val result = treeRegions.count(canFitPresents(presents))
  println(result)
}

private def canFitPresents(presents: List[Present])(treeRegion: TreeRegion) = {
  val presentIndexToPresent = presents.map(p => p.index -> p).toMap
  val presentsArea = treeRegion.presentIndexToQuantity.map { (presentIndex, quantity) =>
    presentIndexToPresent(presentIndex).area * quantity
  }.sum
  val presentsRectangularArea = treeRegion.presentIndexToQuantity.map { (presentIndex, quantity) =>
    val present = presentIndexToPresent(presentIndex)
    present.width * present.height * quantity
  }.sum
  if (treeRegion.area < presentsArea) // impossible case
    false
  else if (treeRegion.area >= presentsRectangularArea) // trivial case, no need to fit
    true
  else // requires fitting
    ??? // TODO: Implement ;)
}
