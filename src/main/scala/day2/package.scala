package io.github.avapl
package day2

type Id = Long

case class IdRange(
    start: Id,
    end: Id
) {
  
  def contains(id: Id): Boolean = 
    (start to end).contains(id)
}
