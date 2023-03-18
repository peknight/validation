package com.peknight.validation.spire.math

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.order.*
import cats.syntax.validated.*
import cats.{Order, Show}
import com.peknight.error.Error
import com.peknight.error.spire.math.IntervalNotContainsError
import spire.math.Interval

object interval:
  object either:
    def contains[N: Order : Show](value: N, interval: Interval[N], label: => String): Either[Error, N] =
      if interval.contains(value) then value.asRight[Error]
      else IntervalNotContainsError[N](label, value, interval).asLeft[N]

    def above[N: Order : Show](value: N, lower: N, label: => String): Either[Error, N] =
      if value > lower then value.asRight[Error]
      else IntervalNotContainsError[N](label, value, Interval.above(lower)).asLeft[N]

    def atOrBelow[N: Order : Show](value: N, upper: N, label: => String): Either[Error, N] =
      if value <= upper then value.asRight[Error]
      else IntervalNotContainsError[N](label, value, Interval.atOrBelow(upper)).asLeft[N]

    def below[N: Order : Show](value: N, upper: N, label: => String): Either[Error, N] =
      if value < upper then value.asRight[Error]
      else IntervalNotContainsError[N](label, value, Interval.below(upper)).asLeft[N]

    def atOrAbove[N: Order : Show](value: N, lower: N, label: => String): Either[Error, N] =
      if value >= lower then value.asRight[Error]
      else IntervalNotContainsError[N](label, value, Interval.atOrAbove(lower)).asLeft[N]

    def positive[N: Order : Show : Numeric](value: N, label: => String): Either[Error, N] =
      above(value, Numeric[N].zero, label)

    def nonPositive[N: Order : Show : Numeric](value: N, label: => String): Either[Error, N] =
      atOrBelow(value, Numeric[N].zero, label)

    def negative[N: Order : Show : Numeric](value: N, label: => String): Either[Error, N] =
      below(value, Numeric[N].zero, label)

    def nonNegative[N: Order : Show : Numeric](value: N, label: => String): Either[Error, N] =
      atOrAbove(value, Numeric[N].zero, label)
  end either

  object validated:
    def contains[N: Order : Show](value: N, interval: Interval[N], label: => String): Validated[Error, N] =
      if interval.contains(value) then value.valid[Error]
      else IntervalNotContainsError[N](label, value, interval).invalid[N]

    def above[N: Order : Show](value: N, lower: N, label: => String): Validated[Error, N] =
      if value > lower then value.valid[Error]
      else IntervalNotContainsError[N](label, value, Interval.above(lower)).invalid[N]

    def atOrBelow[N: Order : Show](value: N, upper: N, label: => String): Validated[Error, N] =
      if value <= upper then value.valid[Error]
      else IntervalNotContainsError[N](label, value, Interval.atOrBelow(upper)).invalid[N]

    def below[N: Order : Show](value: N, upper: N, label: => String): Validated[Error, N] =
      if value < upper then value.valid[Error]
      else IntervalNotContainsError[N](label, value, Interval.below(upper)).invalid[N]

    def atOrAbove[N: Order : Show](value: N, lower: N, label: => String): Validated[Error, N] =
      if value >= lower then value.valid[Error]
      else IntervalNotContainsError[N](label, value, Interval.atOrAbove(lower)).invalid[N]

    def positive[N: Order : Show : Numeric](value: N, label: => String): Validated[Error, N] =
      above(value, Numeric[N].zero, label)

    def nonPositive[N: Order : Show : Numeric](value: N, label: => String): Validated[Error, N] =
      atOrBelow(value, Numeric[N].zero, label)

    def negative[N: Order : Show : Numeric](value: N, label: => String): Validated[Error, N] =
      below(value, Numeric[N].zero, label)

    def nonNegative[N: Order : Show : Numeric](value: N, label: => String): Validated[Error, N] =
      atOrAbove(value, Numeric[N].zero, label)
  end validated

end interval