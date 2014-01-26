package au.gov.cer.registry.smallunits.task.configuration

import com.twitter.finatra.FinatraServer
import au.gov.cer.registry.smallunits.task.service.{TaskGeneratorService, RandomProvider, TaskFilterInPredicate}
import au.gov.cer.registry.smallunits.task.controller.{EnsureJsonRequestFilter, TaskGeneratorController}
import au.gov.cer.registry.smallunits.task.controller.Services.TasksGenerator
import au.gov.cer.registry.smallunits.task.vo.Task
import au.gov.cer.registry.smallunits.task.randomgenerator.RandomGenerator

object TaskApp extends FinatraServer {

  Global.init
  private val randomProvider: RandomProvider = RandomGenerator
  private val filterInPredicate: (Task => Boolean) = TaskFilterInPredicate
  private val tasksGenerator: TasksGenerator = new TaskGeneratorService(randomProvider, filterInPredicate)
  private val ensureJsonRequestFilter: EnsureJsonRequestFilter = new EnsureJsonRequestFilter

  private val taskGeneratorController: TaskGeneratorController = new TaskGeneratorController(tasksGenerator)

//  addFilter(ensureJsonRequestFilter)
  register(taskGeneratorController)
}
