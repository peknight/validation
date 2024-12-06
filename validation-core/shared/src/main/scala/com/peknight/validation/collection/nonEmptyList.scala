package com.peknight.validation.collection

import cats.Eq
import cats.data.{NonEmptyList, Validated}
import cats.syntax.either.*
import cats.syntax.eq.*
import cats.syntax.validated.*
import com.peknight.error.collection.ElementInconsistent

object nonEmptyList:
  object either:
    def elementConsistent[A, B: Eq](nel: NonEmptyList[A])(f: A => B): Either[ElementInconsistent, B] =
      val value = f(nel.head)
      if nel.tail.forall(a => f(a) === value) then value.asRight else ElementInconsistent.asLeft
  end either
  object validated:
    def elementConsistent[A, B: Eq](nel: NonEmptyList[A])(f: A => B): Validated[ElementInconsistent, B] =
      val value = f(nel.head)
      if nel.tail.forall(a => f(a) === value) then value.valid else ElementInconsistent.invalid
  end validated
end nonEmptyList
