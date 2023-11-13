package com.peknight.validation.collection

import cats.data.{NonEmptyList, Validated}
import cats.syntax.either.*
import cats.syntax.validated.*
import com.peknight.error.collection.CollectionEmpty

object list:
  object either:
    def nonEmpty[A](list: List[A]): Either[CollectionEmpty, NonEmptyList[A]] =
      list match
        case head :: tail => NonEmptyList(head, tail).asRight[CollectionEmpty]
        case Nil => CollectionEmpty.asLeft[NonEmptyList[A]]
  end either

  object validated:
    def nonEmpty[A](list: List[A]): Validated[CollectionEmpty, NonEmptyList[A]] =
      list match
        case head :: tail => NonEmptyList(head, tail).valid[CollectionEmpty]
        case Nil => CollectionEmpty.invalid[NonEmptyList[A]]
  end validated

end list
