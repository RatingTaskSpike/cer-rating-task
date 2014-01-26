package httpClient

import au.gov.cer.registry.smallunits.task.vo.Task
import rx.Observer

/**
 * Created by DennyCloud on 14-1-19.
 */
class TaskObserver extends Observer[java.util.List[Task]] {
  def onCompleted(): Unit = {
    println("completed.....")
  }

  def onError(e: Throwable): Unit = {
    throw new IllegalStateException("error...........")
  }

  def onNext(tasks: java.util.List[Task]): Unit = {
    println("processing task...."  + tasks.size())
  }
}
