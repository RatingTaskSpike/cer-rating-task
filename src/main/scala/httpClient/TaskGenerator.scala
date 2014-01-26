package httpClient

import org.apache.http.impl.nio.client.{HttpAsyncClients, CloseableHttpAsyncClient}
import rx.Observable
import au.gov.cer.registry.smallunits.task.vo.Task
import rx.apache.http.{ObservableHttp, ObservableHttpResponse}
import org.apache.http.nio.client.methods.HttpAsyncMethods
import com.twitter.finatra.ContentType
import org.apache.http.entity.ContentType.APPLICATION_JSON
import rx.util.functions.Func1

class TaskGenerator() {

  val httpClient = HttpAsyncClients.createDefault()
  val responseToTasks = new ResponseToTasks


  def generateTask(): Observable[java.util.List[Task]] = {
    httpClient.start
    val request:String = "{\"MAP\": 30, \"COMPLIANCE_PAPER_WORK\": 50, \"PHONE\": 60}"
     val responseObservable: Observable[ObservableHttpResponse] = ObservableHttp.createRequest(HttpAsyncMethods
       .createPost("http://localhost:3000/task-generator", request, APPLICATION_JSON), httpClient).toObservable

    val result = responseObservable.flatMap(new Func1[ObservableHttpResponse, Observable[java.util.List[Task]]]() {
      def call(response: ObservableHttpResponse): Observable[java.util.List[Task]] = {
        response.getContent.map(responseToTasks)
      }
    })
    result
  }

}


class ResponseToTasks extends Func1[Array[Byte], java.util.List[Task]] {

  val converter: AgentRatingTaskToResponseConverter123  = new AgentRatingTaskToResponseConverter123

  def call(bytes: Array[Byte]): java.util.List[Task] = {
    val responseString: String = new String(bytes)
    println("here..............." + responseString)
    converter.convert(responseString)
  }
}