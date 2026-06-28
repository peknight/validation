import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

ThisBuild / scalacOptions --= Seq("-Werror", "-Xfatal-warnings")

lazy val validation = (project in file("."))
  .settings(name := "validation")
  .aggregate(validationCore.projectRefs *)
  .aggregate(validationSpire.projectRefs *)

lazy val validationCore = (projectMatrix in file("validation-core"))
  .settings(name := "validation-core")
  .settings(libraryDependencies ++= dependencies(peknight.error))
  .jvmPlatform(scalaVersions = Seq(scala.scala3.version))
  .jsPlatform(scalaVersions = Seq(scala.scala3.version))
  .nativePlatform(scalaVersions = Seq(scala.scala3.version))

lazy val validationSpire = (projectMatrix in file("validation-spire"))
  .dependsOn(validationCore)
  .settings(name := "validation-spire")
  .settings(libraryDependencies ++= dependencies(peknight.error.spire))
  .jvmPlatform(scalaVersions = Seq(scala.scala3.version))
  .jsPlatform(scalaVersions = Seq(scala.scala3.version))
