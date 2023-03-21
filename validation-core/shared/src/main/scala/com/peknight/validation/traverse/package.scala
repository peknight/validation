package com.peknight.validation

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.traverse.*
import cats.{Semigroup, Traverse}
import com.peknight.error.Error
import com.peknight.error.traverse.TraverseError.toTraverseError
import com.peknight.error.traverse.{TraverseError, TraverseErrorShow}

package object traverse:
  object either:
    def traverse[F[_] : Traverse, A, B](fa: F[A], label: => String, message: => String)(f: A => Either[Error, B])
    : Either[TraverseError[F[A]], F[B]] =
      fa.traverse(a => f(a).toValidated).toEither.left.map(_.toTraverseError(label, fa, message))

    def traverse[F[_] : Traverse, A, B](fa: F[A], label: => String)(using TraverseErrorShow[F[A]])
                                       (f: A => Either[Error, B]): Either[TraverseError[F[A]], F[B]] =
      fa.traverse(a => f(a).toValidated).toEither.left.map(_.toTraverseError(label, fa))
  end either

  object validated:
    def traverse[F[_] : Traverse, A, B](fa: F[A], label: => String, message: => String)(f: A => Validated[Error, B])
    : Validated[TraverseError[F[A]], F[B]] =
      fa.traverse(f).leftMap(_.toTraverseError(label, fa, message))

    def traverse[F[_] : Traverse, A, B](fa: F[A], label: => String)(using TraverseErrorShow[F[A]])
                                       (f: A => Validated[Error, B]): Validated[TraverseError[F[A]], F[B]] =
      fa.traverse(f).leftMap(_.toTraverseError(label, fa))
  end validated
end traverse
