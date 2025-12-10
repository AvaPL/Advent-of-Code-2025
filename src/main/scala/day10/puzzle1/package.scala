package io.github.avapl
package day10.puzzle1

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

case class Button(indices: Set[Int])

case class Machine(
    indicatorLights: List[IndicatorLightState],
    buttons: Set[Button]
)
