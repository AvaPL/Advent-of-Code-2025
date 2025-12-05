package io.github.avapl
package day5

type Id = Long

case class FreshIdRange(
    start: Id,
    end: Id
) {
  val length: Long = end - start + 1
  
  def contains(id: Id): Boolean =
    start <= id && id <= end
}
