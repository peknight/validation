ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.7.1"

ThisBuild / organization := "com.peknight"

ThisBuild / publishTo := {
  val nexus = "https://nexus.peknight.com/repository"
  if (isSnapshot.value)
    Some("snapshot" at s"$nexus/maven-snapshots/")
  else
    Some("releases" at s"$nexus/maven-releases/")
}

ThisBuild / credentials ++= Seq(
  Credentials(Path.userHome / ".sbt" / ".credentials")
)

ThisBuild / resolvers ++= Seq(
  "Pek Nexus" at "https://nexus.peknight.com/repository/maven-public/",
)

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
    validationSpire.jvm,
    validationSpire.js,
  )
  .settings(commonSettings)
  .settings(
    name := "validation",
  )

lazy val validationCore = (crossProject(JSPlatform, JVMPlatform) in file("validation-core"))
  .settings(commonSettings)
  .settings(
    name := "validation-core",
    libraryDependencies ++= Seq(
      "com.peknight" %%% "error-core" % pekErrorVersion,
    ),
  )

lazy val validationSpire = (crossProject(JSPlatform, JVMPlatform) in file("validation-spire"))
  .dependsOn(validationCore)
  .settings(commonSettings)
  .settings(
    name := "validation-spire",
    libraryDependencies ++= Seq(
      "com.peknight" %%% "error-spire" % pekErrorVersion,
    ),
  )

val pekErrorVersion = "0.1.0-SNAPSHOT"
