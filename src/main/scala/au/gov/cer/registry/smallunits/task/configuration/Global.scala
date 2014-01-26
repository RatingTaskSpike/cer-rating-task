package au.gov.cer.registry.smallunits.task.configuration

object Global {

  def init() = {
    System.setProperty("com.twitter.finatra.config.port", ":3000")
    System.setProperty("com.twitter.finatra.config.logLevel", "INFO")
  }

}
