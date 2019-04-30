scalaVersion := "2.12.8"

lazy val hello = (project in file("hello"))
.settings(
  name := "scala-jvm-hello",
  libraryDependencies ++= Seq(
    "com.amazonaws" % "aws-lambda-java-core" % "1.2.0",
    "io.argonaut" %% "argonaut" % "6.2.2"
  )
)

lazy val hello2 = (project in file("hello2"))
  .settings(
    name := "scala-jvm-hello",
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-lambda-java-core" % "1.2.0",
      "com.typesafe.play" %% "play-json" % "2.7.2"
    )
  )

lazy val fibonacci = (project in file("fibonacci"))
.settings(
  name := "scala-jvm-fibonacci",
  libraryDependencies ++= Seq(
    "com.amazonaws" % "aws-lambda-java-core" % "1.2.0",
    "io.argonaut" %% "argonaut" % "6.2.2"
  )
)



