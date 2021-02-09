name := "escale_test"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(  
  "org.apache.spark" %% "spark-core" % "2.2.0",
  "org.apache.spark" %% "spark-sql" % "2.2.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "com.tumblr" % "colossus_2.11" % "0.9.0",
  "org.scala-lang.modules" % "scala-async_2.12" % "0.10.0",
  "net.caoticode.dirwatcher" %% "dir-watcher" % "0.1.0",
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

