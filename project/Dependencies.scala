import play.sbt.PlayImport._
import sbt._

object Dependencies {

  val sparkVersion = "2.1.0"
  val spark_core = "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
  val spark_sql = "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"
  val spark_mllib = "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided"
  val spark_hive = "org.apache.spark" %% "spark-hive" % sparkVersion % "provided"

  val playVersion = "2.5.8" // also configured in project/plugins.sbt
  val play_json = "com.typesafe.play" %% "play-json" % playVersion exclude("com.fasterxml.jackson.core", "jackson-databind")
  val play_ws = "com.typesafe.play" %% "play-ws" % playVersion exclude("com.fasterxml.jackson.core", "jackson-databind") exclude("oauth.signpost", "signpost-commonshttp")

  val play_server = "com.typesafe.play" %% "play-server" % playVersion
  val play_netty_server = "com.typesafe.play" %% "play-netty-server" % playVersion

  val jackson_core = "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.2"
  val jackson_module = "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.2"


  // We use this old version of scala-logging, because epic uses it
  // It was not possible to include epic and use a newer version of scala-logging
  // The error was simliar to this: https://github.com/typesafehub/scala-logging/issues/68
  // Downgrading the library was a fast workaround, shading probably would have been cleaner, but also
  // seems complicated: https://github.com/wsargent/shade-with-sbt-assembly
  val scala_logging = "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"

  // Exclude slf4j-simple, slf4j allows only one binding and we do not want to be forced which binding to use, by epic
  // 
  val scalanlp_epic = "org.scalanlp" %% "epic" % "0.3.1" exclude("org.slf4j", "slf4j-simple")
  val scalanlp_epic_models = "org.scalanlp" %% "english"  % "2015.1.25" exclude("org.slf4j", "slf4j-simple")

  val skinnyVersion = "2.3.6"
  val skinny_orm = "org.skinny-framework" %% "skinny-orm" % skinnyVersion
  val skinny_orm_test = "org.skinny-framework" %% "skinny-test" % skinnyVersion

  val scalikejdbc = "org.scalikejdbc" %% "scalikejdbc" % "2.5.0"
  val scalikejdbc_config = "org.scalikejdbc" %% "scalikejdbc-config"  % "2.5.0"
  val scalikejdbc_play_initlzr = "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.5.1"

  val commons_io = "commons-io" % "commons-io" % "2.5"

  val csv_reader = "com.github.tototoshi" %% "scala-csv" % "1.3.4"

  val protobuf = "com.google.protobuf" % "protobuf-java" % "2.6.1"
  val chill = "com.twitter" %% "chill-bijection" % "0.8.0"

  val postgres = "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"

  val scalaz = "org.scalaz" %% "scalaz-core" % "7.2.5"
  val scalactic = "org.scalactic" %% "scalactic" % "3.0.0"
  val breeze = "org.scalanlp" %% "breeze" % "0.11.2"

  val config = "com.typesafe" % "config" % "1.3.0"
  val scalatest = "org.scalatest" %% "scalatest" % "2.2.4"

  val scopt = "com.github.scopt" %% "scopt" % "3.5.0"


  val baseDeps = Seq(
    config,
    breeze,
    scala_logging,
    scalanlp_epic, scalanlp_epic_models,
    protobuf,
    chill,
    postgres,
    scalatest
    //logback,
  )

  val commonDeps = baseDeps ++ Seq(
    play_json,
    jackson_core,
    jackson_module,
    scopt,
    spark_core,
    spark_sql,
    spark_mllib,
    spark_hive,
    scalikejdbc,
    scalikejdbc_config,
    commons_io,
    skinny_orm,
    skinny_orm_test,
    csv_reader
  )

  val bingImagesDeps = baseDeps ++ Seq(
    play_ws,
    play_server % Test,
    play_netty_server % Test
  )

  val sparkDeps = baseDeps ++ Seq(
    spark_core,
    spark_sql,
    spark_mllib,
    spark_hive,
    scopt
  )

  val apiDeps = baseDeps ++ Seq(
    // Play is configured via PlayScala plugin in build.sbt!
    jdbc,
    cache,
    filters,
    scalikejdbc_play_initlzr,
    javaWs,
    specs2 % Test
  )

}
