package au.gov.cer.registry.smallunits.task.service

import au.gov.cer.registry.smallunits.task.controller.Services._
import au.gov.cer.registry.smallunits.task.vo.Task

class TaskGeneratorService(randomProvider: RandomProvider, filterInPredicate: (Task => Boolean)) extends TasksGenerator {

  override def apply(taskAndRatings: Map[String, Double]) : Set[Task] = {
    taskAndRatings
      .map    { case (task, rating) => (task, rating, randomProvider.next) }
      .filter { case (task, rating, random) => rating.toInt <= random}
      .map    { case (task, rating, random) => Task(task)}
      .filter {filterInPredicate}
      .toSet
  }
}


