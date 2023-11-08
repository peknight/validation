package com.peknight.validation

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.validated.*
import com.peknight.error.Error
import com.peknight.error.option.OptionEmpty

package object option:
  object either:
    def nonEmpty[A](option: Option[A]): Either[OptionEmpty, A] =
      option match
        case Some(a) => a.asRight[OptionEmpty]
        case _ => OptionEmpty.asLeft[A]

    def nonEmpty[A](option: Option[A], label: => String): Either[Error, A] =
      option match
        case Some(a) => a.asRight[Error]
        case _ => OptionEmpty(label).asLeft[A]
  end either
  object validated:
    def nonEmpty[A](option: Option[A]): Validated[OptionEmpty, A] =
      option match
        case Some(a) => a.valid[OptionEmpty]
        case _ => OptionEmpty.invalid[A]

    def nonEmpty[A](option: Option[A], label: => String): Validated[Error, A] =
      option match
        case Some(a) => a.valid[Error]
        case _ => OptionEmpty(label).invalid[A]
  end validated
end option
