name := """mongo-db-akash"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "1.1.0"

libraryDependencies ++= Seq(
  "org.reactivemongo" %% "reactivemongo" % "0.11.11"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
