package com.peknight.validation.collection

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.validated.*
import com.peknight.error.collection.CollectionEmpty

object iterableOnce:
  object either:
    def nonEmpty[A, C <: IterableOnce[A]](c: C): Either[CollectionEmpty, C] =
      if c.iterator.hasNext then c.asRight[CollectionEmpty]
      else CollectionEmpty.asLeft[C]
  end either

  object validated:
    def nonEmpty[A, C <: IterableOnce[A]](c: C): Validated[CollectionEmpty, C] =
      if c.iterator.hasNext then c.valid[CollectionEmpty]
      else CollectionEmpty.invalid[C]
  end validated

end iterableOnce
