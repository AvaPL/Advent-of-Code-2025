package io.github.avapl
package day6

import day6.Operator.{Addition, Multiplication}

sealed trait Operator extends ((Long, Long) => Long)

object Operator {

  object Addition extends Operator {
    override def apply(v1: Long, v2: Long): Long = v1 + v2
  }

  object Multiplication extends Operator {
    override def apply(v1: Long, v2: Long): Long = v1 * v2
  }
}

case class Problem(numbers: List[Long], operator: Operator) {

  def evaluate: Long = numbers.reduce(operator)
}
