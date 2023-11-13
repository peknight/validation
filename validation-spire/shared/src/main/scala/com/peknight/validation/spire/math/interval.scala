package com.peknight.validation.spire.math

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.order.*
import cats.syntax.validated.*
import cats.{Order, Show}
import com.peknight.error.spire.math.interval.{IntervalEmpty, IntervalNotContains, NonValueBound}
import spire.math.Interval
import spire.math.interval.{Bound, ValueBound}

object interval:
  object either:
    def nonEmpty[A](interval: Interval[A]): Either[IntervalEmpty, Interval[A]] =
      if interval.nonEmpty then interval.asRight[IntervalEmpty]
      else IntervalEmpty.asLeft[Interval[A]]

    def contains[N: Order: Show](value: N, interval: Interval[N]): Either[IntervalNotContains[N], N] =
      if interval.contains(value) then value.asRight[IntervalNotContains[N]]
      else IntervalNotContains(value, interval).asLeft[N]

    def above[N: Order: Show](value: N, lower: N): Either[IntervalNotContains[N], N] =
      if value > lower then value.asRight[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.above(lower)).asLeft[N]

    def atOrBelow[N: Order: Show](value: N, upper: N): Either[IntervalNotContains[N], N] =
      if value <= upper then value.asRight[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.atOrBelow(upper)).asLeft[N]

    def below[N: Order: Show](value: N, upper: N): Either[IntervalNotContains[N], N] =
      if value < upper then value.asRight[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.below(upper)).asLeft[N]

    def atOrAbove[N: Order: Show](value: N, lower: N): Either[IntervalNotContains[N], N] =
      if value >= lower then value.asRight[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.atOrAbove(lower)).asLeft[N]

    def positive[N: Order: Numeric: Show](value: N): Either[IntervalNotContains[N], N] =
      above(value, Numeric[N].zero)

    def nonPositive[N: Order: Numeric: Show](value: N): Either[IntervalNotContains[N], N] =
      atOrBelow(value, Numeric[N].zero)

    def negative[N: Order: Numeric: Show](value: N): Either[IntervalNotContains[N], N] =
      below(value, Numeric[N].zero)

    def nonNegative[N: Order: Numeric: Show](value: N): Either[IntervalNotContains[N], N] =
      atOrAbove(value, Numeric[N].zero)

    def valueBounded[A](bound: Bound[A]): Either[NonValueBound, Bound[A]] =
      bound match
        case _: ValueBound[_] => bound.asRight[NonValueBound]
        case _ => NonValueBound.asLeft[Bound[A]]

    def lowerValueBounded[A](interval: Interval[A]): Either[NonValueBound, Interval[A]] =
      valueBounded(interval.lowerBound).map(_ => interval)

    def upperValueBounded[A](interval: Interval[A]): Either[NonValueBound, Interval[A]] =
      valueBounded(interval.upperBound).map(_ => interval)
  end either

  object validated:

    def nonEmpty[A](interval: Interval[A]): Validated[IntervalEmpty, Interval[A]] =
      if interval.nonEmpty then interval.valid[IntervalEmpty]
      else IntervalEmpty.invalid[Interval[A]]

    def contains[N: Order: Show](value: N, interval: Interval[N]): Validated[IntervalNotContains[N], N] =
      if interval.contains(value) then value.valid[IntervalNotContains[N]]
      else IntervalNotContains(value, interval).invalid[N]

    def above[N: Order: Show](value: N, lower: N): Validated[IntervalNotContains[N], N] =
      if value > lower then value.valid[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.above(lower)).invalid[N]

    def atOrBelow[N: Order: Show](value: N, upper: N): Validated[IntervalNotContains[N], N] =
      if value <= upper then value.valid[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.atOrBelow(upper)).invalid[N]

    def below[N: Order: Show](value: N, upper: N): Validated[IntervalNotContains[N], N] =
      if value < upper then value.valid[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.below(upper)).invalid[N]

    def atOrAbove[N: Order: Show](value: N, lower: N): Validated[IntervalNotContains[N], N] =
      if value >= lower then value.valid[IntervalNotContains[N]]
      else IntervalNotContains(value, Interval.atOrAbove(lower)).invalid[N]

    def positive[N: Order: Numeric: Show](value: N): Validated[IntervalNotContains[N], N] =
      above(value, Numeric[N].zero)

    def nonPositive[N: Order: Numeric: Show](value: N): Validated[IntervalNotContains[N], N] =
      atOrBelow(value, Numeric[N].zero)

    def negative[N: Order: Numeric: Show](value: N): Validated[IntervalNotContains[N], N] =
      below(value, Numeric[N].zero)

    def nonNegative[N: Order: Numeric: Show](value: N): Validated[IntervalNotContains[N], N] =
      atOrAbove(value, Numeric[N].zero)

    def valueBounded[A](bound: Bound[A]): Validated[NonValueBound, Bound[A]] =
      bound match
        case _: ValueBound[_] => bound.valid[NonValueBound]
        case _ => NonValueBound.invalid[Bound[A]]

    def lowerValueBounded[A](interval: Interval[A]): Validated[NonValueBound, Interval[A]] =
      valueBounded(interval.lowerBound).map(_ => interval)

    def upperValueBounded[A](interval: Interval[A]): Validated[NonValueBound, Interval[A]] =
      valueBounded(interval.upperBound).map(_ => interval)
  end validated

end interval