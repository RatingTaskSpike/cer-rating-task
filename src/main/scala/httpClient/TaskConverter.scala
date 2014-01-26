package httpClient

import au.gov.cer.registry.smallunits.task.vo.Task
import org.codehaus.jackson.map.ObjectMapper

class TaskConverter {

//  def  convert(responseString: java.lang.String) :List[Task] = {
//    val objectMapper: ObjectMapper = new ObjectMapper()
//    try {
//      val contents = objectMapper.readValue(responseString, java.util.List.class)
//
//      val taskContents = contents.asInstanceOf[List[Map[String, String]]]
//      taskContents.map {x => {
//        println("............." + x.get("name").toString)
//        new Task(x.get("name").toString)
//      }}
//    }catch {
//      case e: Exception => {
//        println(e)
//      throw new RuntimeException("Error parsing JSON response: " + e);
//    }
//  }
//  }
}
