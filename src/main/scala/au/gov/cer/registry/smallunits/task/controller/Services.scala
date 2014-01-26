package au.gov.cer.registry.smallunits.task.controller

import au.gov.cer.registry.smallunits.task.vo.Task

object Services {

  type TasksGenerator = (Map[String, Double] => Set[Task])

}
