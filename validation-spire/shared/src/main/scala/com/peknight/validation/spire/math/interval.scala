package com.peknight.validation.spire.math

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.order.*
import cats.syntax.validated.*
import cats.{Order, Show}
import com.peknight.error.Error
import com.peknight.error.spire.math.{IntervalEmpty, IntervalNotContains}
import spire.math.Interval

object interval:
  object either:
    def nonEmpty[A](interval: Interval[A]): Either[IntervalEmpty, Interval[A]] =
      if interval.nonEmpty then interval.asRight[IntervalEmpty]
      else IntervalEmpty.asLeft[Interval[A]]

    def nonEmpty[A](interval: Interval[A], label: => String): Either[Error, Interval[A]] =
      if interval.nonEmpty then interval.asRight[Error]
      else IntervalEmpty(label).asLeft[Interval[A]]

    def contains[N: Order](value: N, interval: Interval[N]): Either[IntervalNotContains[N], N] =
      if interval.contains(value) then value.asRight[IntervalNotContains[N]]
      else IntervalNotContains(value, interval).asLeft[N]

    def contains[N: Order: Show](value: N, interval: Interval[N], label: => String): Either[Error, N] =
      if interval.contains(value) then value.asRight[Error]
      else IntervalNotContains(value, interval, label).asLeft[N]

    def above[N: Order](value: N, lower: N): Either[IntervalNotContains[N], N] =
      if value > lower then value.asRight[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.above(lower)).asLeft[N]

    def above[N: Order: Show](value: N, lower: N, label: => String): Either[Error, N] =
      if value > lower then value.asRight[Error]
      else IntervalNotContains(value, Interval.above(lower), label).asLeft[N]

    def atOrBelow[N: Order](value: N, upper: N): Either[IntervalNotContains[N], N] =
      if value <= upper then value.asRight[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.atOrBelow(upper)).asLeft[N]

    def atOrBelow[N: Order: Show](value: N, upper: N, label: => String): Either[Error, N] =
      if value <= upper then value.asRight[Error]
      else IntervalNotContains(value, Interval.atOrBelow(upper), label).asLeft[N]

    def below[N: Order](value: N, upper: N): Either[IntervalNotContains[N], N] =
      if value < upper then value.asRight[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.below(upper)).asLeft[N]

    def below[N: Order: Show](value: N, upper: N, label: => String): Either[Error, N] =
      if value < upper then value.asRight[Error]
      else IntervalNotContains(value, Interval.below(upper), label).asLeft[N]

    def atOrAbove[N: Order](value: N, lower: N): Either[IntervalNotContains[N], N] =
      if value >= lower then value.asRight[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.atOrAbove(lower)).asLeft[N]

    def atOrAbove[N: Order: Show](value: N, lower: N, label: => String): Either[Error, N] =
      if value >= lower then value.asRight[Error]
      else IntervalNotContains(value, Interval.atOrAbove(lower), label).asLeft[N]

    def positive[N: Order: Numeric](value: N): Either[IntervalNotContains[N], N] =
      above(value, Numeric[N].zero)

    def positive[N: Order: Numeric: Show](value: N, label: => String): Either[Error, N] =
      above(value, Numeric[N].zero, label)

    def nonPositive[N: Order: Numeric](value: N): Either[IntervalNotContains[N], N] =
      atOrBelow(value, Numeric[N].zero)

    def nonPositive[N: Order: Numeric: Show](value: N, label: => String): Either[Error, N] =
      atOrBelow(value, Numeric[N].zero, label)

    def negative[N: Order: Numeric](value: N): Either[IntervalNotContains[N], N] =
      below(value, Numeric[N].zero)

    def negative[N: Order: Numeric: Show](value: N, label: => String): Either[Error, N] =
      below(value, Numeric[N].zero, label)

    def nonNegative[N: Order: Numeric](value: N): Either[IntervalNotContains[N], N] =
      atOrAbove(value, Numeric[N].zero)

    def nonNegative[N: Order: Numeric: Show](value: N, label: => String): Either[Error, N] =
      atOrAbove(value, Numeric[N].zero, label)
  end either

  object validated:

    def nonEmpty[A](interval: Interval[A]): Validated[IntervalEmpty, Interval[A]] =
      if interval.nonEmpty then interval.valid[IntervalEmpty]
      else IntervalEmpty.invalid[Interval[A]]

    def nonEmpty[A](interval: Interval[A], label: => String): Validated[Error, Interval[A]] =
      if interval.nonEmpty then interval.valid[Error]
      else IntervalEmpty(label).invalid[Interval[A]]

    def contains[N: Order](value: N, interval: Interval[N]): Validated[IntervalNotContains[N], N] =
      if interval.contains(value) then value.valid[IntervalNotContains[N]]
      else IntervalNotContains(value, interval).invalid[N]

    def contains[N: Order: Show](value: N, interval: Interval[N], label: => String): Validated[Error, N] =
      if interval.contains(value) then value.valid[Error]
      else IntervalNotContains(value, interval, label).invalid[N]

    def above[N: Order](value: N, lower: N): Validated[IntervalNotContains[N], N] =
      if value > lower then value.valid[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.above(lower)).invalid[N]

    def above[N: Order: Show](value: N, lower: N, label: => String): Validated[Error, N] =
      if value > lower then value.valid[Error]
      else IntervalNotContains(value, Interval.above(lower), label).invalid[N]

    def atOrBelow[N: Order](value: N, upper: N): Validated[IntervalNotContains[N], N] =
      if value <= upper then value.valid[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.atOrBelow(upper)).invalid[N]

    def atOrBelow[N: Order: Show](value: N, upper: N, label: => String): Validated[Error, N] =
      if value <= upper then value.valid[Error]
      else IntervalNotContains(value, Interval.atOrBelow(upper), label).invalid[N]

    def below[N: Order](value: N, upper: N): Validated[IntervalNotContains[N], N] =
      if value < upper then value.valid[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.below(upper)).invalid[N]

    def below[N: Order: Show](value: N, upper: N, label: => String): Validated[Error, N] =
      if value < upper then value.valid[Error]
      else IntervalNotContains(value, Interval.below(upper), label).invalid[N]

    def atOrAbove[N: Order](value: N, lower: N): Validated[IntervalNotContains[N], N] =
      if value >= lower then value.valid[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.atOrAbove(lower)).invalid[N]

    def atOrAbove[N: Order: Show](value: N, lower: N, label: => String): Validated[Error, N] =
      if value >= lower then value.valid[Error]
      else IntervalNotContains(value, Interval.atOrAbove(lower), label).invalid[N]

    def positive[N: Order: Numeric](value: N): Validated[IntervalNotContains[N], N] =
      above(value, Numeric[N].zero)

    def positive[N: Order: Numeric: Show](value: N, label: => String): Validated[Error, N] =
      above(value, Numeric[N].zero, label)

    def nonPositive[N: Order: Numeric](value: N): Validated[IntervalNotContains[N], N] =
      atOrBelow(value, Numeric[N].zero)

    def nonPositive[N: Order: Numeric: Show](value: N, label: => String): Validated[Error, N] =
      atOrBelow(value, Numeric[N].zero, label)

    def negative[N: Order: Numeric](value: N): Validated[IntervalNotContains[N], N] =
      below(value, Numeric[N].zero)

    def negative[N: Order: Numeric: Show](value: N, label: => String): Validated[Error, N] =
      below(value, Numeric[N].zero, label)

    def nonNegative[N: Order: Numeric](value: N): Validated[IntervalNotContains[N], N] =
      atOrAbove(value, Numeric[N].zero)

    def nonNegative[N: Order: Numeric: Show](value: N, label: => String): Validated[Error, N] =
      atOrAbove(value, Numeric[N].zero, label)
  end validated

end interval