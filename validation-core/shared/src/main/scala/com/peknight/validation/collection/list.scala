package com.peknight.validation.collection

import cats.data.{NonEmptyList, Validated}
import cats.syntax.either.*
import cats.syntax.validated.*
import com.peknight.error.Error
import com.peknight.error.collection.CollectionEmptyError

object list:
  object either:
    def nonEmpty[A](list: List[A], label: => String): Either[Error, NonEmptyList[A]] = list match
      case head :: tail => NonEmptyList(head, tail).asRight[Error]
      case Nil => CollectionEmptyError(label).asLeft[NonEmptyList[A]]
  end either

  object validated:
    def nonEmpty[A](list: List[A], label: => String): Validated[Error, NonEmptyList[A]] = list match
      case head :: tail => NonEmptyList(head, tail).valid[Error]
      case Nil => CollectionEmptyError(label).invalid[NonEmptyList[A]]
  end validated

end list
