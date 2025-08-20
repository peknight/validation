import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

lazy val validation = (project in file("."))
  .settings(name := "validation")
  .aggregate(
    validationCore.jvm,
    validationCore.js,
    validationCore.native,
    validationSpire.jvm,
    validationSpire.js,
  )

lazy val validationCore = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("validation-core"))
  .settings(name := "validation-core")
  .settings(crossDependencies(peknight.error))

lazy val validationSpire = (crossProject(JVMPlatform, JSPlatform) in file("validation-spire"))
  .dependsOn(validationCore)
  .settings(name := "validation-spire")
  .settings(crossDependencies(peknight.error.spire))
