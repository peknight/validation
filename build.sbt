import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

lazy val validation = (project in file("."))
  .aggregate(
    validationCore.jvm,
    validationCore.js,
    validationCore.native,
    validationSpire.jvm,
    validationSpire.js,
  )
  .settings(
    name := "validation",
  )

lazy val validationCore = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("validation-core"))
  .settings(crossDependencies(peknight.error))
  .settings(
    name := "validation-core",
  )

lazy val validationSpire = (crossProject(JVMPlatform, JSPlatform) in file("validation-spire"))
  .settings(crossDependencies(peknight.error.spire))
  .dependsOn(validationCore)
  .settings(
    name := "validation-spire",
  )
