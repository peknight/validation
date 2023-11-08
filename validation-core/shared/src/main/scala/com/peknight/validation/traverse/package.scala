package com.peknight.validation

import cats.Traverse
import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.traverse.*
import com.peknight.error.Error

package object traverse:
  object either:
    def traverse[F[_] : Traverse, E, A, B](fa: F[A])(f: A => Either[E, B]): Either[Error, F[B]] =
      fa.traverse(a => f(a).toValidated.leftMap(Error.pure)).toEither
  end either

  object validated:
    def traverse[F[_] : Traverse, E, A, B](fa: F[A])(f: A => Validated[E, B]): Validated[Error, F[B]] =
      fa.traverse(a => f(a).leftMap(Error.pure))
  end validated
end traverse
