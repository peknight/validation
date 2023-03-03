ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"

ThisBuild / organization := "com.peknight"

lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-unchecked",
    "-Xfatal-warnings",
    "-language:strictEquality",
    "-Xmax-inlines:64"
  ),
)

lazy val validation = (project in file("."))
  .aggregate(
    validationCore.jvm,
    validationCore.js,
    validationGeneric.jvm,
    validationGeneric.js,
    validationSpire.jvm,
    validationSpire.js,
  )
  .enablePlugins(JavaAppPackaging)
  .settings(commonSettings)
  .settings(
    name := "validation",
  )

lazy val validationCore = (crossProject(JSPlatform, JVMPlatform) in file("validation-core"))
  .settings(commonSettings)
  .settings(
    name := "validation-core",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % catsVersion,
      "com.peknight" %%% "error-core" % pekErrorVersion,
    ),
  )

lazy val validationGeneric = (crossProject(JSPlatform, JVMPlatform) in file("validation-generic"))
  .dependsOn(validationCore)
  .settings(commonSettings)
  .settings(
    name := "validation-generic",
    libraryDependencies ++= Seq(
      "com.peknight" %%% "generic-core" % pekGenericVersion,
    ),
  )

lazy val validationSpire = (crossProject(JSPlatform, JVMPlatform) in file("validation-spire"))
  .settings(commonSettings)
  .settings(
    name := "validation-spire",
    libraryDependencies ++= Seq(
      "com.peknight" %%% "error-spire" % pekErrorVersion,
    ),
  )

val catsVersion = "2.9.0"
val pekErrorVersion = "0.1.0-SNAPSHOT"
val pekGenericVersion = "0.1.0-SNAPSHOT"
