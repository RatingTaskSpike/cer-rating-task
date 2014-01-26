package integration.task

import com.twitter.finatra.test.FlatSpecHelper
import au.gov.cer.registry.smallunits.task.configuration.TaskApp

class TaskGeneratorIntegrationSpec extends FlatSpecHelper {

  val server = TaskApp

  "GET /task-generator" should "respond with 404 (Not Found)" in {
    get(path = "/task-generator", headers = Map("content-type" -> "application/json"))
    response.body   should equal ("Not Found")
    response.code   should equal (404)
  }

  "POST /task-generator with no body" should "respond with 400 (Bad Request)" in {
    post(path = "/task-generator", headers = Map("content-type" -> "application/json"))
    response.code   should equal (400)
    response.body   should equal ("Can not parse the request body")
  }

}
