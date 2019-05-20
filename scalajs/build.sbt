lazy val commonSettings = Seq(
  scalaVersion := "2.12.8",
  scalacOptions += "-P:scalajs:sjsDefinedByDefault",
  scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }
)

lazy val hello = (project in file("hello"))
  .settings(
    commonSettings,
    artifactPath in (Compile, fastOptJS) := baseDirectory.value / "dist" / "hello.js",
  ).enablePlugins(ScalaJSPlugin)


lazy val takeuchi = (project in file("takeuchi"))
  .settings(
    commonSettings,
    artifactPath in (Compile, fastOptJS) := baseDirectory.value / "dist" / "takeuchi.js",
  ).enablePlugins(ScalaJSPlugin)

lazy val crawler = (project in file("crawler"))
.settings(
  commonSettings,
  libraryDependencies += "com.softwaremill.sttp" %%% "core" % "1.5.14",
  artifactPath in (Compile, fastOptJS) := baseDirectory.value / "dist" / "crawler.js",
).enablePlugins(ScalaJSPlugin)

lazy val audit = (project in file("audit"))
  .settings(
    commonSettings,
    libraryDependencies += "com.softwaremill.sttp" %%% "core" % "1.5.14",
    artifactPath in (Compile, fastOptJS) := baseDirectory.value / "dist" / "audit.js",
  ).enablePlugins(ScalaJSPlugin)

lazy val dynamo = (project in file("dynamo"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp" %%% "core" % "1.5.14",
      "net.exoego" %%% "aws-sdk-scalajs-facade" % "0.21.0",
      "io.scalajs" %%% "nodejs" % "0.4.2"
    ),
    artifactPath in (Compile, fastOptJS) := baseDirectory.value / "dist" / "dynamo.js",
  ).enablePlugins(ScalaJSPlugin)