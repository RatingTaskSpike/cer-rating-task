package httpClient

import rx.Observable
import au.gov.cer.registry.smallunits.task.vo.Task

/**
 * Created by DennyCloud on 14-1-19.
 */
class HttpRunner {

  def run = {
    val generator: TaskGenerator = new TaskGenerator

    val tasks: Observable[java.util.List[Task]] = generator.generateTask

    tasks.subscribe(new TaskObserver)

    println(tasks)
  }
}
