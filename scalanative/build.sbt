lazy val commonSettings = Seq(
  scalaVersion := "2.11.12",
  resolvers += "mmreleases" at "https://artifactory.mediamath.com/artifactory/libs-release-global",
  nativeLinkingOptions ++= Seq("-static-libstdc++", "-L/lib/"),
  nativeGC := "immix" // default setting requires addtional libs such as Boehm GC
)

lazy val hello = (project in file("hello"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp" %%% "core" % "1.5.11",
      "io.argonaut" % "argonaut_native0.3_2.11" % "6.2.2"
    )
  ).enablePlugins(ScalaNativePlugin)

lazy val fibonacci = (project in file("fibonacci"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp" %%% "core" % "1.5.11",
      "io.argonaut" % "argonaut_native0.3_2.11" % "6.2.2"
    )
  ).enablePlugins(ScalaNativePlugin)


