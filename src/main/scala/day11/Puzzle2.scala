package io.github.avapl
package day11

@main def puzzle2(): Unit = {
  val devices = PuzzleInputParser.parsedInput
  val result = countValidSvrOutPaths(devices)
  println(result)
}

private def countValidSvrOutPaths(devices: List[Device]) = {
  val svrDeviceId = "svr"
  val dacDeviceId = "dac"
  val fftDeviceId = "fft"
  val outDeviceId = "out"

  List(
    List(svrDeviceId, dacDeviceId, fftDeviceId, outDeviceId),
    List(svrDeviceId, fftDeviceId, dacDeviceId, outDeviceId)
  ).map {
    _.sliding(2)
      .map {
        case List(from, to) => countPaths(devices, from, to)
      }
      .product
  }.sum
}
