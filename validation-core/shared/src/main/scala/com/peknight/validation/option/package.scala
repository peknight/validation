package com.peknight.validation

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.validated.*
import com.peknight.error.option.{OptionEmptyError, OptionEmptyErrorShow}

package object option:
  object either:
    def nonEmpty[A](option: Option[A], label: => String, message: => String): Either[OptionEmptyError, A] =
      option match
        case Some(a) => a.asRight[OptionEmptyError]
        case _ => OptionEmptyError(label, message).asLeft[A]

    def nonEmpty[A](option: Option[A], label: => String)(using OptionEmptyErrorShow): Either[OptionEmptyError, A] =
      option match
        case Some(a) => a.asRight[OptionEmptyError]
        case _ => OptionEmptyError(label).asLeft[A]
  end either
  object validated:
    def nonEmpty[A](option: Option[A], label: => String, message: => String): Validated[OptionEmptyError, A] =
      option match
        case Some(a) => a.valid[OptionEmptyError]
        case _ => OptionEmptyError(label, message).invalid[A]

    def nonEmpty[A](option: Option[A], label: => String)(using OptionEmptyErrorShow): Validated[OptionEmptyError, A] =
      option match
        case Some(a) => a.valid[OptionEmptyError]
        case _ => OptionEmptyError(label).invalid[A]
  end validated
end option
