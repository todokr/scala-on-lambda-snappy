lazy val commonSettings = Seq(
  scalaVersion := "2.12.8"
)

lazy val hello = (project in file("hello"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp" %% "core" % "1.5.11",
      "io.argonaut" %% "argonaut" % "6.2.2"
    )
  )

lazy val takeuchi = (project in file("takeuchi"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp" %% "core" % "1.5.11",
      "io.argonaut" %% "argonaut" % "6.2.2"
    )
  )
