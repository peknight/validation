package com.peknight.validation.validated

import cats.data.ValidatedNel
import com.peknight.error.Error

trait Validation[A]:
  def validate(a: A): ValidatedNel[Error, A]
end Validation
object Validation:
  trait Labelled[A]:
    def validate(a: A, label: => String): ValidatedNel[Error, A]
  end Labelled
end Validation