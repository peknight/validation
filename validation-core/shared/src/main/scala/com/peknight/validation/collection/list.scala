package com.peknight.validation.collection

import cats.Show
import cats.data.{NonEmptyList, Validated}
import cats.syntax.either.*
import cats.syntax.validated.*
import com.peknight.error.collection.{CollectionEmpty, CollectionError, MoreThenOneElement}

object list:
  object either:
    def nonEmpty[A](list: List[A]): Either[CollectionEmpty, NonEmptyList[A]] =
      list match
        case head :: tail => NonEmptyList(head, tail).asRight[CollectionEmpty]
        case Nil => CollectionEmpty.asLeft[NonEmptyList[A]]
    def one[A: Show](list: List[A]): Either[CollectionError, A] =
      list match
        case head :: Nil => head.asRight[CollectionError]
        case head :: tail => MoreThenOneElement(list).asLeft[A]
        case _ => CollectionEmpty.asLeft[A]
  end either

  object validated:
    def nonEmpty[A](list: List[A]): Validated[CollectionEmpty, NonEmptyList[A]] =
      list match
        case head :: tail => NonEmptyList(head, tail).valid[CollectionEmpty]
        case Nil => CollectionEmpty.invalid[NonEmptyList[A]]
    def one[A: Show](list: List[A]): Validated[CollectionError, A] =
      list match
        case head :: Nil => head.valid[CollectionError]
        case head :: tail => MoreThenOneElement(list).invalid[A]
        case _ => CollectionEmpty.invalid[A]
  end validated

end list
