package com.peknight.validation.collection

import cats.data.{NonEmptyList, Validated}
import cats.syntax.either.*
import cats.syntax.validated.*
import com.peknight.error.Error
import com.peknight.error.collection.{CollectionEmptyError, CollectionEmptyErrorShow, CollectionEmptyErrorT}

object list:
  object either:
    def nonEmpty[A, Ext](list: List[A], label: => String, ext: => Ext, message: => String)
    : Either[CollectionEmptyErrorT[Ext], NonEmptyList[A]] =
      list match
        case head :: tail => NonEmptyList(head, tail).asRight[CollectionEmptyErrorT[Ext]]
        case Nil => CollectionEmptyError(label, ext, message).asLeft[NonEmptyList[A]]

    def nonEmpty[A](list: List[A], label: => String, message: => String)
    : Either[CollectionEmptyError, NonEmptyList[A]] =
      list match
        case head :: tail => NonEmptyList(head, tail).asRight[CollectionEmptyError]
        case Nil => CollectionEmptyError(label, message).asLeft[NonEmptyList[A]]

    def nonEmpty[A](list: List[A], label: => String)(using CollectionEmptyErrorShow)
    : Either[CollectionEmptyError, NonEmptyList[A]] =
      list match
        case head :: tail => NonEmptyList(head, tail).asRight[CollectionEmptyError]
        case Nil => CollectionEmptyError(label).asLeft[NonEmptyList[A]]
  end either

  object validated:
    def nonEmpty[A, Ext](list: List[A], label: => String, ext: => Ext, message: => String)
    : Validated[CollectionEmptyErrorT[Ext], NonEmptyList[A]] =
      list match
        case head :: tail => NonEmptyList(head, tail).valid[CollectionEmptyErrorT[Ext]]
        case Nil => CollectionEmptyError(label, ext, message).invalid[NonEmptyList[A]]

    def nonEmpty[A](list: List[A], label: => String, message: => String)
    : Validated[CollectionEmptyError, NonEmptyList[A]] =
      list match
        case head :: tail => NonEmptyList(head, tail).valid[CollectionEmptyError]
        case Nil => CollectionEmptyError(label, message).invalid[NonEmptyList[A]]

    def nonEmpty[A](list: List[A], label: => String)(using CollectionEmptyErrorShow)
    : Validated[CollectionEmptyError, NonEmptyList[A]] =
      list match
        case head :: tail => NonEmptyList(head, tail).valid[CollectionEmptyError]
        case Nil => CollectionEmptyError(label).invalid[NonEmptyList[A]]
  end validated

end list
