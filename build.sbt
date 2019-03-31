
import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "io.github.todokr"

lazy val jvm = (project in file("jvm"))
  .settings(
    name := "jvm",
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-lambda-java-core" % "1.2.0",
    )
  )

lazy val scalaJs = (project in file("scalajs"))
  .settings(
    name := "scalaJs",
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    artifactPath in (Compile, fastOptJS) := baseDirectory.value / "dist" / "hello.js",
  ).enablePlugins(ScalaJSPlugin)

lazy val scalaNative = (project in file("scalanative"))
  .settings(
    name := "scalaNative",
    scalaVersion := "2.11.12",
    resolvers += "mmreleases" at "https://artifactory.mediamath.com/artifactory/libs-release-global",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp" %%% "core" % "1.5.4",
      "com.mediamath" %%% "scala-json" % "1.0"
    ),
    nativeCompileOptions += "-I/usr/local/opt/curl/include",
    nativeLinkingOptions += "-L/usr/local/opt/curl/lib",
  ).enablePlugins(ScalaNativePlugin)

lazy val graalVm = (project in file("graalvm"))
  .settings(
    name := "graalVm",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp" %% "core" % "1.5.11",
      "io.spray" %%  "spray-json" % "1.3.5"
    )
  )
