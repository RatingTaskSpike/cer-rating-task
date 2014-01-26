package au.gov.cer.registry.smallunits.task.service

import au.gov.cer.registry.smallunits.task.vo.Task

object TaskFilterInPredicate extends (Task => Boolean) {

  private val validTasks: Set[String] = Set("map", "compliance_paper_work", "phone")

  override def apply(task: Task): Boolean = validTasks.contains(task.getName.toLowerCase)
}
