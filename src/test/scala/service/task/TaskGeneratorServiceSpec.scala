package service.task

import org.specs2.specification.Scope
import org.junit.runner.RunWith
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import au.gov.cer.registry.smallunits.task.service.{TaskFilterInPredicate, RandomProvider, TaskGeneratorService}


@RunWith(classOf[JUnitRunner])
class TaskGeneratorServiceSpec extends Specification with Mockito {

  "Task generator service" should {
    "return tasks which rating is lower than the random rating" in new Subject {
      val tasks : Map[String, Double] = Map("phone" -> 30, "map" -> 60)
      val random: Int = 30
      mockGenerator.next returns random

      service(tasks).foreach(x => x.getName mustEqual "phone" )
    }

    "return no tasks which rating is higher than the random rating" in new Subject {
      val tasks : Map[String, Double] = Map("phone" -> 30, "map" -> 60)
      val random: Int = 15
      mockGenerator.next returns random

      service(tasks).size mustEqual 0
    }

    "the times call random generator next rating should be same to the number of the tasks" in new Subject {
      val tasks : Map[String, Double] = Map("whatever" -> 30, "map" -> 7, "phone" -> 3)

      service(tasks)

      there was 3.times(mockGenerator).next
    }
  }

  trait Subject extends Scope {
    val mockGenerator = mock[RandomProvider]
    val service = new TaskGeneratorService(mockGenerator, TaskFilterInPredicate)
  }
}
