package config

object Environment {

  val conf = com.typesafe.config.ConfigFactory.load()

  def getJsonSourceFolder(): String = {
    conf.getString("Configuration.Env.Json_SourceFolder")
  }
}
