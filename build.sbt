import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "io.github.todokr"

lazy val root = (project in file("."))
  .aggregate(pureScala)

lazy val pureScala = (project in file("purescala"))
  .settings(
    name := "pure-scala"
  )

lazy val scalaJs = (project in file("scalajs"))
  .settings(
    name := "scala-js"
  )

lazy val graalvm = (project in file("graalvm"))
  .settings(
    name := "graal-vm"
  )
