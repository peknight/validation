package com.peknight.validation

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.validated.*
import com.peknight.error.option.OptionEmpty

package object option:
  object either:
    def nonEmpty[A](option: Option[A]): Either[OptionEmpty, A] =
      option match
        case Some(a) => a.asRight[OptionEmpty]
        case _ => OptionEmpty.asLeft[A]
  end either
  object validated:
    def nonEmpty[A](option: Option[A]): Validated[OptionEmpty, A] =
      option match
        case Some(a) => a.valid[OptionEmpty]
        case _ => OptionEmpty.invalid[A]
  end validated
end option
