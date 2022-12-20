name := "learning_scala"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.12"

//lazy val root = (project in file("."))
//  .settings(
//    name := "learning_scala"
//  )

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.0.0",
  "org.apache.spark" %% "spark-sql" % "3.0.0",
  "org.apache.spark" %% "spark-mllib" % "3.0.0",
  "org.apache.spark" %% "spark-streaming" % "3.0.0",
)

// https://mvnrepository.com/artifact/com.github.tototoshi/scala-csv
libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.4"



//we can also add dependencies in this way
//libraryDependencies += "org.apache.spark" %% "spark-core" % "3.3.1"
