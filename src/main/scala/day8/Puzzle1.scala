package io.github.avapl
package day8

import scala.collection.mutable

@main def puzzle1(): Unit = {
  val junctionBoxesPositions = PuzzleInputParser.parsedInput
  val pairs = junctionBoxesPairs(junctionBoxesPositions)
  val circuits = connectCircuits(pairs, maxConnections = 1000)
  val result = circuits.map(_.size).sorted.takeRight(3).product
  println(result)
}

private def connectCircuits(junctionBoxesPairs: List[(Position, Position)], maxConnections: Int) = {
  val circuits = mutable.ListBuffer.empty[Set[Position]]
  var connectionCount = 0

  val pairsByDistanceAsc = junctionBoxesPairs.sortBy(_.distance(_)).iterator
  while (pairsByDistanceAsc.hasNext && connectionCount < maxConnections) {
    val pairPositionSet = pairsByDistanceAsc.next.toList.toSet
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
    connectionCount += 1
  }

  circuits.toList
}
