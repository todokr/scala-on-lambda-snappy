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

lazy val fibonacci = (project in file("fibonacci"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp" %% "core" % "1.5.11",
      "io.argonaut" %% "argonaut" % "6.2.2"
    )
  )
