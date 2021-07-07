lazy val root = (project in file("."))
  .settings(
    name := "scala-rsa",

    version := "1.0",

    scalaVersion := "2.11.8",

    libraryDependencies ++= Seq(
      "javax.xml.bind" % "jaxb-api" % "2.3.1"
     ),

    mainClass := Some("example.Main")
)
