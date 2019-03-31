
import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "io.github.todokr"

lazy val root = (project in file("."))
  .aggregate(jvm)

lazy val jvm = (project in file("jvm"))
  .settings(
    name := "jvm",
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-lambda-java-core" % "1.2.0",
    )
  )

lazy val scalaJs = (project in file("scalajs"))
  .settings(
    name := "scala-js",
    libraryDependencies ++= Seq(
      "net.exoego" %%% "aws-sdk-scalajs-facade" % "0.21.0"
    ),
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    artifactPath in (Compile, fastOptJS) := baseDirectory.value / "dist" / "hello.js",
  ).enablePlugins(ScalaJSPlugin)

lazy val scalaNative = (project in file("scalanative"))
  .settings(
    name := "scala-native"
  )

lazy val graalvm = (project in file("graalvm"))
  .settings(
    name := "graal-vm"
  )
