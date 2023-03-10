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
    def contains[N: Order : Show](value: N, label: => String, interval: Interval[N]): Either[Error, N] =
      if interval.contains(value) then value.asRight[Error]
      else IntervalNotContainsError[N](value, label, interval).asLeft[N]

    def above[N: Order : Show](value: N, label: => String, lower: N): Either[Error, N] =
      if value > lower then value.asRight[Error]
      else IntervalNotContainsError[N](value, label, Interval.above(lower)).asLeft[N]

    def atOrBelow[N: Order : Show](value: N, label: => String, upper: N): Either[Error, N] =
      if value <= upper then value.asRight[Error]
      else IntervalNotContainsError[N](value, label, Interval.atOrBelow(upper)).asLeft[N]

    def below[N: Order : Show](value: N, label: => String, upper: N): Either[Error, N] =
      if value < upper then value.asRight[Error]
      else IntervalNotContainsError[N](value, label, Interval.below(upper)).asLeft[N]

    def atOrAbove[N: Order : Show](value: N, label: => String, lower: N): Either[Error, N] =
      if value >= lower then value.asRight[Error]
      else IntervalNotContainsError[N](value, label, Interval.atOrAbove(lower)).asLeft[N]

    def positive[N: Order : Show : Numeric](value: N, label: => String): Either[Error, N] =
      above(value, label, Numeric[N].zero)

    def nonPositive[N: Order : Show : Numeric](value: N, label: => String): Either[Error, N] =
      atOrBelow(value, label, Numeric[N].zero)

    def negative[N: Order : Show : Numeric](value: N, label: => String): Either[Error, N] =
      below(value, label, Numeric[N].zero)

    def nonNegative[N: Order : Show : Numeric](value: N, label: => String): Either[Error, N] =
      atOrAbove(value, label, Numeric[N].zero)
  end either

  object validated:
    def contains[N: Order : Show](value: N, label: => String, interval: Interval[N]): Validated[Error, N] =
      if interval.contains(value) then value.valid[Error]
      else IntervalNotContainsError[N](value, label, interval).invalid[N]

    def above[N: Order : Show](value: N, label: => String, lower: N): Validated[Error, N] =
      if value > lower then value.valid[Error]
      else IntervalNotContainsError[N](value, label, Interval.above(lower)).invalid[N]

    def atOrBelow[N: Order : Show](value: N, label: => String, upper: N): Validated[Error, N] =
      if value <= upper then value.valid[Error]
      else IntervalNotContainsError[N](value, label, Interval.atOrBelow(upper)).invalid[N]

    def below[N: Order : Show](value: N, label: => String, upper: N): Validated[Error, N] =
      if value < upper then value.valid[Error]
      else IntervalNotContainsError[N](value, label, Interval.below(upper)).invalid[N]

    def atOrAbove[N: Order : Show](value: N, label: => String, lower: N): Validated[Error, N] =
      if value >= lower then value.valid[Error]
      else IntervalNotContainsError[N](value, label, Interval.atOrAbove(lower)).invalid[N]

    def positive[N: Order : Show : Numeric](value: N, label: => String): Validated[Error, N] =
      above(value, label, Numeric[N].zero)

    def nonPositive[N: Order : Show : Numeric](value: N, label: => String): Validated[Error, N] =
      atOrBelow(value, label, Numeric[N].zero)

    def negative[N: Order : Show : Numeric](value: N, label: => String): Validated[Error, N] =
      below(value, label, Numeric[N].zero)

    def nonNegative[N: Order : Show : Numeric](value: N, label: => String): Validated[Error, N] =
      atOrAbove(value, label, Numeric[N].zero)
  end validated

end interval