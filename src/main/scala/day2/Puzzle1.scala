package io.github.avapl
package day2

@main def puzzle1(): Unit = {
  val idRanges = PuzzleInputParser.parsedInput
  val invalidIds = idRanges.flatMap {
    case IdRange(start, end) =>
      (firstEvenLengthFrom(start, maxId = end), lastEvenLengthTo(minId = start, end)) match {
        case (Some(evenLengthId), Some(maxEvenLengthId)) =>
          invalidIdsFor(evenLengthId, maxEvenLengthId)
        case _ => Nil
      }
  }
  val result = invalidIds.sum
  println(result)
}

private def firstEvenLengthFrom(id: Id, maxId: Id): Option[Long] = {
  val length = id.toString.length
  if (length % 2 == 0) Some(id)
  else Some(math.pow(10, length).toLong).filter(_ <= maxId)
}

private def lastEvenLengthTo(minId: Id, id: Id): Option[Long] = {
  val length = id.toString.length
  if (length % 2 == 0) Some(id)
  else if (length == 1) None
  else Some(math.pow(10, length - 1).toLong - 1).filter(minId <= _)
}

private def invalidIdsFor(evenLengthId: Id, maxEvenLengthId: Id) = {
  val evenLengthIdString = evenLengthId.toString
  val prefix = evenLengthIdString.splitAt(evenLengthIdString.length / 2) match {
    case (prefixString, postfixString) =>
      val prefix = prefixString.toLong
      val postfix = postfixString.toLong
      // In ranges like 580816-616131 we want to start with 581 prefix, because 580580 is outside the range
      if (prefix >= postfix) prefix
      else prefix + 1
  }
  List.unfold(prefix) { prefix =>
    val invalidId = s"$prefix$prefix".toLong
    Option.when(invalidId <= maxEvenLengthId)((invalidId, prefix + 1))
  }
}
