package de.tudarmstadt.lt.wsd.common.model

import com.typesafe.scalalogging.LazyLogging

/**
  * Created by fide on 16.04.17.
  */
object SenseInventory extends Enumeration {

  def getInstance(model: SenseVectorModel): SenseInventory = {
    if (model.isInventoryCoSet) {
      CoSetSenseInventory.getInstance(model)
    } else {
      new TraditionalSenseInventory(model)
    }

  }
}

abstract class SenseInventory {
  def getSenses(word: String): List[SenseVector]
}

object CoSetSenseInventory extends LazyLogging {
  // Instance cache: because for each word, all senses have to be retrieved
  private var instances: Map[SenseVectorModel, CoSetSenseInventory] = Map()

  def getInstance(model: SenseVectorModel): CoSetSenseInventory = {
    if (! instances.contains(model)) {
      instances = instances + (model -> new CoSetSenseInventory(model))
      logger.info(s"No CoSetSenseInventory existed for model '$model', had to create it!")
    } else {
      logger.info(s"Using cached CoSetSenseInventory for model '$model'")
    }

    instances(model)
  }
}

class CoSetSenseInventory(model: SenseVectorModel) extends SenseInventory {
  val globalSenses: List[SenseVector] =
    SenseVector.findAllByModel(model)
  override def getSenses(word: String): List[SenseVector] = globalSenses
}

class TraditionalSenseInventory(model: SenseVectorModel) extends SenseInventory {
  override def getSenses(word: String): List[SenseVector] =
    SenseVector.findAllByModelAndCaseIgnoredWord(model, word)
}
