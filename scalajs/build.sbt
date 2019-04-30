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


lazy val fibonacci = (project in file("fibonacci"))
  .settings(
    commonSettings,
    artifactPath in (Compile, fastOptJS) := baseDirectory.value / "dist" / "fibonacci.js",
  ).enablePlugins(ScalaJSPlugin)
