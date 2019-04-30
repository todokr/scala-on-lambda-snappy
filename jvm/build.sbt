scalaVersion := "2.12.8"

lazy val hello = (project in file("hello"))
.settings(
  name := "scala-jvm-hello",
  libraryDependencies ++= Seq(
    "com.amazonaws" % "aws-lambda-java-core" % "1.2.0"
  )
)

lazy val fibonacci = (project in file("fibonacci"))
.settings(
  name := "scala-jvm-fibonacci",
  libraryDependencies ++= Seq(
    "com.amazonaws" % "aws-lambda-java-core" % "1.2.0"
  )
)



