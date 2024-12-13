package com.peknight.validation.collection

import cats.data.Validated
import cats.syntax.either.*
import cats.syntax.eq.*
import cats.syntax.functor.*
import cats.syntax.option.*
import cats.syntax.validated.*
import cats.{Eq, Show}
import com.peknight.error.collection.{CollectionEmpty, CollectionNoInteraction, CollectionNotContains}

object iterableOnce:
  object either:
    def nonEmpty[A, C <: IterableOnce[A]](collection: C): Either[CollectionEmpty, C] =
      if collection.iterator.hasNext then collection.asRight[CollectionEmpty]
      else CollectionEmpty.asLeft[C]
    def contains[A: Eq: Show, C <: IterableOnce[A]](value: A, collection: C): Either[CollectionNotContains[A, C], A] =
      collection.iterator.find(_ === value).as(value).toRight(CollectionNotContains(value, collection))
    def interact[A: Eq: Show, C1 <: IterableOnce[A], C2 <: IterableOnce[A]](c1: C1, c2: C2)
    : Either[CollectionNoInteraction[A, C1, C2], C1] =
      if c1.iterator.exists(a => c2.iterator.exists(_ === a)) then c1.asRight
      else CollectionNoInteraction(c1, c2).asLeft
  end either

  object validated:
    def nonEmpty[A, C <: IterableOnce[A]](collection: C): Validated[CollectionEmpty, C] =
      if collection.iterator.hasNext then collection.valid[CollectionEmpty]
      else CollectionEmpty.invalid[C]
    def contains[A: Eq: Show, C <: IterableOnce[A]](value: A, collection: C): Validated[CollectionNotContains[A, C], A] =
      collection.iterator.find(_ === value).as(value).toValid(CollectionNotContains(value, collection))
    def interact[A: Eq: Show, C1 <: IterableOnce[A], C2 <: IterableOnce[A]](c1: C1, c2: C2)
    : Validated[CollectionNoInteraction[A, C1, C2], C1] =
      if c1.iterator.exists(a => c2.iterator.exists(_ === a)) then c1.valid
      else CollectionNoInteraction(c1, c2).invalid
  end validated
end iterableOnce
