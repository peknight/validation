package com.peknight.validation.spire.math.interval.id.validated

import cats.data.ValidatedNel
import cats.syntax.order.*
import cats.syntax.validated.*
import cats.{Id, Order, Show}
import com.peknight.error.Error
import com.peknight.error.spire.math.IntervalError
import spire.math.Interval

object IntervalValidation:
  def interval[N : Order : Show](value: N, label: => String, interval: Interval[N]): ValidatedNel[Error, N] =
    if interval.contains(value) then value.validNel[Error]
    else IntervalError[Id, N](value, label, interval).invalidNel[N]

  def above[N : Order : Show](value: N, label: => String, lower: N): ValidatedNel[Error, N] =
    if value > lower then value.validNel[Error]
    else IntervalError[Id, N](value, label, Interval.above(lower)).invalidNel[N]

  def atOrBelow[N: Order : Show](value: N, label: => String, upper: N): ValidatedNel[Error, N] =
    if value <= upper then value.validNel[Error]
    else IntervalError[Id, N](value, label, Interval.atOrBelow(upper)).invalidNel[N]

  def below[N: Order : Show](value: N, label: => String, upper: N): ValidatedNel[Error, N] =
    if value < upper then value.validNel[Error]
    else IntervalError[Id, N](value, label, Interval.below(upper)).invalidNel[N]

  def atOrAbove[N: Order : Show](value: N, label: => String, lower: N): ValidatedNel[Error, N] =
    if value >= lower then value.validNel[Error]
    else IntervalError[Id, N](value, label, Interval.atOrAbove(lower)).invalidNel[N]

  def positive[N: Order : Show : Numeric](value: N, label: => String): ValidatedNel[Error, N] =
    above(value, label, Numeric[N].zero)

  def nonPositive[N: Order : Show : Numeric](value: N, label: => String): ValidatedNel[Error, N] =
    atOrBelow(value, label, Numeric[N].zero)

  def negative[N: Order : Show : Numeric](value: N, label: => String): ValidatedNel[Error, N] =
    below(value, label, Numeric[N].zero)

  def nonNegative[N: Order : Show : Numeric](value: N, label: => String): ValidatedNel[Error, N] =
    atOrAbove(value, label, Numeric[N].zero)
end IntervalValidation
