package io.github.avapl
package day10

sealed trait IndicatorLightState {
  def toggle: IndicatorLightState
}

object IndicatorLightState {

  object On extends IndicatorLightState {
    override val toggle: IndicatorLightState = Off
  }

  object Off extends IndicatorLightState {
    override val toggle: IndicatorLightState = On
  }
}

case class IndicatorLight(
    state: IndicatorLightState,
    joltageRequirement: Int
) {

  lazy val off: IndicatorLight = copy(state = IndicatorLightState.Off)
}

case class Button(
    indicatorLightIndices: Set[Int]
)

case class MachineState(
    indicatorLights: List[IndicatorLight]
) {

  lazy val off: MachineState = copy(indicatorLights = indicatorLights.map(_.off))

  def push(button: Button): MachineState =
    MachineState(
      indicatorLights.zipWithIndex.map {
        case (indicatorLight @ IndicatorLight(state, _), index) if button.indicatorLightIndices.contains(index) =>
          indicatorLight.copy(state = state.toggle)
        case (indicatorLight, _) => indicatorLight
      }
    )
}

case class Machine(
    state: MachineState,
    buttons: Set[Button]
)
