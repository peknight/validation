package com.peknight.validation.collection

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.validated.*
import com.peknight.error.collection.{CollectionEmptyError, CollectionEmptyErrorShow}

object iterableOnce:
  object either:
    def nonEmpty[A, C <: IterableOnce[A]](c: C, label: => String, message: => String)
    : Either[CollectionEmptyError, C] =
      if c.iterator.hasNext then c.asRight[CollectionEmptyError]
      else CollectionEmptyError(label, message).asLeft[C]

    def nonEmpty[A, C <: IterableOnce[A]](c: C, label: => String)(using CollectionEmptyErrorShow)
    : Either[CollectionEmptyError, C] =
      if c.iterator.hasNext then c.asRight[CollectionEmptyError]
      else CollectionEmptyError(label).asLeft[C]
  end either

  object validated:
    def nonEmpty[A, C <: IterableOnce[A]](c: C, label: => String, message: => String)
    : Validated[CollectionEmptyError, C] =
      if c.iterator.hasNext then c.valid[CollectionEmptyError]
      else CollectionEmptyError(label, message).invalid[C]

    def nonEmpty[A, C <: IterableOnce[A]](c: C, label: => String)(using CollectionEmptyErrorShow)
    : Validated[CollectionEmptyError, C] =
      if c.iterator.hasNext then c.valid[CollectionEmptyError]
      else CollectionEmptyError(label).invalid[C]
  end validated

end iterableOnce
