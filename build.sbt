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
    validationSpire.jvm,
    validationSpire.js,
  )
  .enablePlugins(JavaAppPackaging)
  .settings(commonSettings)
  .settings(
    name := "validation",
  )

lazy val validationSpire = (crossProject(JSPlatform, JVMPlatform) in file("validation-spire"))
  .settings(commonSettings)
  .settings(
    name := "validation-spire",
    libraryDependencies ++= Seq(
      "com.peknight" %%% "error-spire" % pekErrorVersion,
    ),
  )

val pekErrorVersion = "0.1.0-SNAPSHOT"
