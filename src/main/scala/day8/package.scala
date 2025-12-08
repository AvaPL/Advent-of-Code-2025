package io.github.avapl
package day8

type Distance = Double

case class Position(
    x: Int,
    y: Int,
    z: Int
) {

  def distance(other: Position): Distance = math.sqrt {
    math.pow(x - other.x, 2) + math.pow(y - other.y, 2) + math.pow(z - other.z, 2)
  }
}

def junctionBoxesPairs(junctionBoxesPositions: List[Position]) =
  junctionBoxesPositions
    .combinations(2)
    .map { case List(from, to) => from -> to }
    .toList
