package com.peknight.validation.either

import com.peknight.error.Error

trait Validation[A]:
  def validate(a: A): Either[Error, A]
end Validation
object Validation:
  trait Labelled[A]:
    def validate(a: A, label: => String): Either[Error, A]
  end Labelled
end Validation