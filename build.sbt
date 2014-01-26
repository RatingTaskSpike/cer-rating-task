name := "cer-rating-task"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra" % "1.5.1",
  "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.13",
  "com.netflix.rxjava" % "rxjava-core" % "0.16.0",
  "com.netflix.rxjava" % "rxjava-apache-http" % "0.16.1",
  "org.apache.httpcomponents" % "httpasyncclient" % "4.0",
  "org.scalatest" %% "scalatest" %"1.9.1" % "test",
  "org.specs2" % "specs2_2.10" % "2.3-scalaz-7.1.0-M3" % "provided",
//  "org.apache.wink" % "wink-client" % "1.4" % "provided",
//  "org.apache.wink" % "wink-common" % "1.4" % "provided",
//  "org.apache.wink" % "wink-server" % "1.4" % "provided",
//  "org.apache.wink" % "wink-component-test-support" % "1.4" % "provided",
  "com.github.kristofa" % "mock-http-server" % "1.3" % "provided",
  "junit" % "junit" % "4.10" % "provided"
)

resolvers +=
  "Twitter" at "http://maven.twttr.com"
