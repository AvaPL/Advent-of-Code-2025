package io.github.avapl
package day11

import scala.collection.mutable

type DeviceId = String

case class Device(
    id: DeviceId,
    connections: Set[DeviceId]
)

def countPaths(devices: List[Device], sourceDeviceId: DeviceId, targetDeviceId: DeviceId) = {
  val deviceIdToConnections = devices.map(device => device.id -> device.connections).toMap.withDefault(_ => Set.empty)
  val deviceIdToPathCount = mutable.Map.empty[DeviceId, Long]

  def loop(sourceDeviceId: DeviceId): Long =
    if (sourceDeviceId == targetDeviceId) 1L
    else
      deviceIdToPathCount.getOrElseUpdate(
        sourceDeviceId,
        deviceIdToConnections(sourceDeviceId).toList.map(loop).sum
      )

  loop(sourceDeviceId)
}
