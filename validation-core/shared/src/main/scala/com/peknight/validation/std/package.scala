package com.peknight.validation

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.validated.*
import com.peknight.error.std.WrongClassTag

import scala.reflect.ClassTag

package object std:
  object either:
    def fold[E, A](flag: Boolean)(e: => E)(a: => Either[E, A]): Either[E, A] =
      if flag then a else e.asLeft
    def isTrue[E](flag: Boolean, e: => E): Either[E, Unit] =
      if flag then ().asRight else e.asLeft
    def isFalse[E](flag: Boolean, e: => E): Either[E, Unit] =
      if !flag then ().asRight else e.asLeft
    def typed[A: ClassTag](a: Any): Either[WrongClassTag[A], A] =
      a match
        case a: A => a.asRight
        case a => WrongClassTag[A](a).asLeft
  end either
  object validated:
    def fold[E, A](flag: Boolean)(e: => E)(a: => Validated[E, A]): Validated[E, A] =
      if flag then a else e.invalid
    def isTrue[E](flag: Boolean, e: => E): Validated[E, Unit] =
      if flag then ().valid else e.invalid
    def isFalse[E](flag: Boolean, e: => E): Validated[E, Unit] =
      if !flag then ().valid else e.invalid
    def typed[A: ClassTag](a: Any): Validated[WrongClassTag[A], A] =
      a match
        case a: A => a.valid
        case a => WrongClassTag[A](a).invalid
  end validated
end std
