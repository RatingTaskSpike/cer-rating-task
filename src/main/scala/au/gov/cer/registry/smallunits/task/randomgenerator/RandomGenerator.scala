package au.gov.cer.registry.smallunits.task.randomgenerator

import scala.util.Random
import au.gov.cer.registry.smallunits.task.service.RandomProvider

object RandomGenerator extends RandomProvider {

  override def next: Int = {
    Random.nextInt(101)
  }
}
