package com.peknight.validation.spire.math

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.order.*
import cats.syntax.validated.*
import cats.{Order, Show}
import com.peknight.error.spire.math.{IntervalEmptyError, IntervalEmptyErrorShow, IntervalNotContainsError, IntervalNotContainsErrorShow}
import spire.math.Interval

object interval:
  object either:
    def nonEmpty[A](interval: Interval[A], label: => String, message: => String)
    : Either[IntervalEmptyError, Interval[A]] =
      if interval.nonEmpty then interval.asRight[IntervalEmptyError]
      else IntervalEmptyError(label, message).asLeft[Interval[A]]

    def nonEmpty[A](interval: Interval[A], label: => String)(using IntervalEmptyErrorShow)
    : Either[IntervalEmptyError, Interval[A]] =
      if interval.nonEmpty then interval.asRight[IntervalEmptyError]
      else IntervalEmptyError(label).asLeft[Interval[A]]

    def contains[N: Order : Show](value: N, interval: Interval[N], label: => String, message: => String)
    : Either[IntervalNotContainsError[N], N] =
      if interval.contains(value) then value.asRight[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, interval, message).asLeft[N]

    def contains[N: Order : Show : IntervalNotContainsErrorShow](value: N, interval: Interval[N], label: => String)
    : Either[IntervalNotContainsError[N], N] =
      if interval.contains(value) then value.asRight[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, interval).asLeft[N]

    def above[N: Order : Show](value: N, lower: N, label: => String, message: => String)
    : Either[IntervalNotContainsError[N], N] =
      if value > lower then value.asRight[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.above(lower), message).asLeft[N]

    def above[N: Order : Show : IntervalNotContainsErrorShow](value: N, lower: N, label: => String)
    : Either[IntervalNotContainsError[N], N] =
      if value > lower then value.asRight[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.above(lower)).asLeft[N]

    def atOrBelow[N: Order : Show](value: N, upper: N, label: => String, message: => String)
    : Either[IntervalNotContainsError[N], N] =
      if value <= upper then value.asRight[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.atOrBelow(upper), message).asLeft[N]

    def atOrBelow[N: Order : Show : IntervalNotContainsErrorShow](value: N, upper: N, label: => String)
    : Either[IntervalNotContainsError[N], N] =
      if value <= upper then value.asRight[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.atOrBelow(upper)).asLeft[N]

    def below[N: Order : Show](value: N, upper: N, label: => String, message: => String)
    : Either[IntervalNotContainsError[N], N] =
      if value < upper then value.asRight[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.below(upper), message).asLeft[N]

    def below[N: Order : Show : IntervalNotContainsErrorShow](value: N, upper: N, label: => String)
    : Either[IntervalNotContainsError[N], N] =
      if value < upper then value.asRight[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.below(upper)).asLeft[N]

    def atOrAbove[N: Order : Show](value: N, lower: N, label: => String, message: => String)
    : Either[IntervalNotContainsError[N], N] =
      if value >= lower then value.asRight[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.atOrAbove(lower), message).asLeft[N]

    def atOrAbove[N: Order : Show : IntervalNotContainsErrorShow](value: N, lower: N, label: => String)
    : Either[IntervalNotContainsError[N], N] =
      if value >= lower then value.asRight[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.atOrAbove(lower)).asLeft[N]

    def positive[N: Order : Show : Numeric](value: N, label: => String, message: => String)
    : Either[IntervalNotContainsError[N], N] =
      above(value, Numeric[N].zero, label, message)

    def positive[N: Order : Show : Numeric : IntervalNotContainsErrorShow](value: N, label: => String)
    : Either[IntervalNotContainsError[N], N] =
      above(value, Numeric[N].zero, label)

    def nonPositive[N: Order : Show : Numeric](value: N, label: => String, message: => String)
    : Either[IntervalNotContainsError[N], N] =
      atOrBelow(value, Numeric[N].zero, label, message)

    def nonPositive[N: Order : Show : Numeric : IntervalNotContainsErrorShow](value: N, label: => String)
    : Either[IntervalNotContainsError[N], N] =
      atOrBelow(value, Numeric[N].zero, label)

    def negative[N: Order : Show : Numeric](value: N, label: => String, message: => String)
    : Either[IntervalNotContainsError[N], N] =
      below(value, Numeric[N].zero, label, message)

    def negative[N: Order : Show : Numeric : IntervalNotContainsErrorShow](value: N, label: => String)
    : Either[IntervalNotContainsError[N], N] =
      below(value, Numeric[N].zero, label)

    def nonNegative[N: Order : Show : Numeric](value: N, label: => String, message: => String)
    : Either[IntervalNotContainsError[N], N] =
      atOrAbove(value, Numeric[N].zero, label, message)

    def nonNegative[N: Order : Show : Numeric : IntervalNotContainsErrorShow](value: N, label: => String)
    : Either[IntervalNotContainsError[N], N] =
      atOrAbove(value, Numeric[N].zero, label)
  end either

  object validated:

    def nonEmpty[A](interval: Interval[A], label: => String, message: => String)
    : Validated[IntervalEmptyError, Interval[A]] =
      if interval.nonEmpty then interval.valid[IntervalEmptyError]
      else IntervalEmptyError(label, message).invalid[Interval[A]]

    def nonEmpty[A](interval: Interval[A], label: => String)(using IntervalEmptyErrorShow)
    : Validated[IntervalEmptyError, Interval[A]] =
      if interval.nonEmpty then interval.valid[IntervalEmptyError]
      else IntervalEmptyError(label).invalid[Interval[A]]

    def contains[N: Order : Show](value: N, interval: Interval[N], label: => String, message: => String)
    : Validated[IntervalNotContainsError[N], N] =
      if interval.contains(value) then value.valid[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, interval, message).invalid[N]

    def contains[N: Order : Show : IntervalNotContainsErrorShow](value: N, interval: Interval[N], label: => String)
    : Validated[IntervalNotContainsError[N], N] =
      if interval.contains(value) then value.valid[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, interval).invalid[N]

    def above[N: Order : Show](value: N, lower: N, label: => String, message: => String)
    : Validated[IntervalNotContainsError[N], N] =
      if value > lower then value.valid[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.above(lower), message).invalid[N]

    def above[N: Order : Show : IntervalNotContainsErrorShow](value: N, lower: N, label: => String)
    : Validated[IntervalNotContainsError[N], N] =
      if value > lower then value.valid[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.above(lower)).invalid[N]

    def atOrBelow[N: Order : Show](value: N, upper: N, label: => String, message: => String)
    : Validated[IntervalNotContainsError[N], N] =
      if value <= upper then value.valid[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.atOrBelow(upper), message).invalid[N]

    def atOrBelow[N: Order : Show : IntervalNotContainsErrorShow](value: N, upper: N, label: => String)
    : Validated[IntervalNotContainsError[N], N] =
      if value <= upper then value.valid[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.atOrBelow(upper)).invalid[N]

    def below[N: Order : Show](value: N, upper: N, label: => String, message: => String)
    : Validated[IntervalNotContainsError[N], N] =
      if value < upper then value.valid[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.below(upper), message).invalid[N]

    def below[N: Order : Show : IntervalNotContainsErrorShow](value: N, upper: N, label: => String)
    : Validated[IntervalNotContainsError[N], N] =
      if value < upper then value.valid[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.below(upper)).invalid[N]

    def atOrAbove[N: Order : Show](value: N, lower: N, label: => String, message: => String)
    : Validated[IntervalNotContainsError[N], N] =
      if value >= lower then value.valid[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.atOrAbove(lower), message).invalid[N]

    def atOrAbove[N: Order : Show : IntervalNotContainsErrorShow](value: N, lower: N, label: => String)
    : Validated[IntervalNotContainsError[N], N] =
      if value >= lower then value.valid[IntervalNotContainsError[N]]
      else IntervalNotContainsError(label, value, Interval.atOrAbove(lower)).invalid[N]

    def positive[N: Order : Show : Numeric](value: N, label: => String, message: => String)
    : Validated[IntervalNotContainsError[N], N] =
      above(value, Numeric[N].zero, label, message)

    def positive[N: Order : Show : Numeric : IntervalNotContainsErrorShow](value: N, label: => String)
    : Validated[IntervalNotContainsError[N], N] =
      above(value, Numeric[N].zero, label)

    def nonPositive[N: Order : Show : Numeric](value: N, label: => String, message: => String)
    : Validated[IntervalNotContainsError[N], N] =
      atOrBelow(value, Numeric[N].zero, label, message)

    def nonPositive[N: Order : Show : Numeric : IntervalNotContainsErrorShow](value: N, label: => String)
    : Validated[IntervalNotContainsError[N], N] =
      atOrBelow(value, Numeric[N].zero, label)

    def negative[N: Order : Show : Numeric](value: N, label: => String, message: => String)
    : Validated[IntervalNotContainsError[N], N] =
      below(value, Numeric[N].zero, label, message)

    def negative[N: Order : Show : Numeric : IntervalNotContainsErrorShow](value: N, label: => String)
    : Validated[IntervalNotContainsError[N], N] =
      below(value, Numeric[N].zero, label)

    def nonNegative[N: Order : Show : Numeric](value: N, label: => String, message: => String)
    : Validated[IntervalNotContainsError[N], N] =
      atOrAbove(value, Numeric[N].zero, label, message)

    def nonNegative[N: Order : Show : Numeric : IntervalNotContainsErrorShow](value: N, label: => String)
    : Validated[IntervalNotContainsError[N], N] =
      atOrAbove(value, Numeric[N].zero, label)
  end validated

end interval