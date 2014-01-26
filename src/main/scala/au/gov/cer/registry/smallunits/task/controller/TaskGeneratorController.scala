package au.gov.cer.registry.smallunits.task.controller

import com.twitter.finatra.Controller
import scala.util.parsing.json.{JSONObject, JSON}
import au.gov.cer.registry.smallunits.task.controller.Services._
import au.gov.cer.registry.smallunits.task.vo.Task

class TaskGeneratorController(taskGenerator: TasksGenerator) extends Controller {

  post("/task-generator") { request =>

    println( "processing : " + JSON.parseRaw(request.contentString))

    JSON.parseRaw(request.contentString) match {
      case Some(JSONObject(rawMap)) => {
        val taskRatingsMap = rawMap.asInstanceOf[Map[String, Double]]
        val tasks: Set[Task] = taskGenerator(taskRatingsMap)
        println("here........")
        render.json(tasks).toFuture
      }
      case _ => invalidRequest
    }
  }

  private def invalidRequest = render.plain("Can not parse the request body").status(400).toFuture
}
