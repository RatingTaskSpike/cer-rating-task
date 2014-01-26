package httpClient

import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import rx.{Observer, Observable}
import au.gov.cer.registry.smallunits.task.vo.Task
import com.harlap.test.http.MockHttpServer
import task.{GeneratedSmallUnitAuditTask, AgentRatingTaskToResponseConverter, AgentRatingBasedTaskGenerator}
import org.apache.http.impl.nio.client.HttpAsyncClients
import rx.apache.http.ObservableHttpResponse

@RunWith(classOf[JUnitRunner])
class Runner extends Specification {

  def startMcokSever = {
    val server: MockHttpServer = new MockHttpServer(30000)
    server.start
    server.expect(MockHttpServer.Method.POST, "/task-generator", """{"MAP": 0, "COMPLIANCE_PAPER_WORK": 0, "PHONE": 0}""").respondWith(200, "application/json", "{\"name\": \"map\"}")
  }

  "Task generator service" should {

    startMcokSever


    "return tasks which rating is lower than the random rating" in new Scope {


      val generator = new AgentRatingBasedTaskGenerator(HttpAsyncClients.createDefault(), new AgentRatingTaskToResponseConverter())

      val observable: Observable[GeneratedSmallUnitAuditTask] = generator.observable()

      observable.subscribe(new DummyObserver)

//      Thread.sleep(10000)
//
//      println(123)
    }
    }

 }

class DummyObserver extends Observer[GeneratedSmallUnitAuditTask] {
  def onCompleted {
    System.out.println("...")
  }

  def onError(e: Throwable) {
    System.out.println("error:" + e)
  }

  def onNext(args: GeneratedSmallUnitAuditTask) {
    System.out.println("...")
  }
}
