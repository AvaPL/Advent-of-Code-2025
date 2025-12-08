package io.github.avapl
package day8

import scala.collection.mutable

@main def puzzle2(): Unit = {
  val junctionBoxesPositions = PuzzleInputParser.parsedInput
  val pairs = junctionBoxesPairs(junctionBoxesPositions)
  val (lastFrom, lastTo) = lastConnectionToFullCircuit(pairs, junctionBoxesCount = junctionBoxesPositions.size)
  val result = lastFrom.x * lastTo.x
  println(result)
}

private def lastConnectionToFullCircuit(junctionBoxesPairs: List[(Position, Position)], junctionBoxesCount: Int) = {
  var lastConnection = Option.empty[(Position, Position)]
  val circuits = mutable.ListBuffer.empty[Set[Position]]

  val pairsByDistanceAsc = junctionBoxesPairs.sortBy(_.distance(_)).iterator
  while (lastConnection.isEmpty) {
    val (from, to) = pairsByDistanceAsc.next()
    val pairPositionSet = Set(from , to)
    val circuitIndices = // junction boxes in a pair may already belong to two different circuits
      circuits.zipWithIndex.collect {
        case (circuit, i) if circuit.intersect(pairPositionSet).nonEmpty => i
      }.toList
    circuitIndices match {
      case Nil =>
        circuits.addOne(pairPositionSet)
      case indices @ head :: tail =>
        val combinedCircuit = indices.toSet.flatMap(circuits(_)).union(pairPositionSet)
        circuits.update(head, combinedCircuit)
        tail.reverse.foreach(circuits.remove)
    }
    if (circuits.size == 1 && circuits.head.size == junctionBoxesCount)
      lastConnection = Some(from -> to)
  }

  lastConnection.get
}
