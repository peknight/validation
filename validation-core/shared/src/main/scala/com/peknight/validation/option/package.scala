package com.peknight.validation

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.validated.*
import com.peknight.error.Error
import com.peknight.error.option.OptionEmptyError

package object option:
  object either:
    def nonEmpty[A](option: Option[A], label: => String): Either[Error, A] = option match
      case Some(a) => a.asRight[Error]
      case _ => OptionEmptyError(label).asLeft[A]
  end either
  object validated:
    def nonEmpty[A](option: Option[A], label: => String): Validated[Error, A] = option match
      case Some(a) => a.valid[Error]
      case _ => OptionEmptyError(label).invalid[A]
  end validated
end option
