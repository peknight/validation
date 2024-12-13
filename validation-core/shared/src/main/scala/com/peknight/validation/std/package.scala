package com.peknight.validation

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.option.*
import cats.syntax.validated.*
import com.peknight.error.std.WrongClassTag

import scala.reflect.ClassTag

package object std:
  object either:
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
    def isTrue[E](flag: Boolean, e: => E): Validated[E, Unit] =
      if flag then ().valid else e.invalid
    def isFalse[E](flag: Boolean, e: => E): Validated[E, Unit] =
      if !flag then ().valid else e.invalid
    def typed[A: ClassTag](a: Any): Validated[WrongClassTag[A], A] =
      a match
        case a: A => a.valid
        case a => WrongClassTag[A](a).invalid
  end validated
  object option:
    def typed[A: ClassTag](a: Any): Option[A] =
      a match
        case a: A => a.some
        case a => none[A]
  end option
end std
