package de.tudarmstadt.lt.wsd.common.model

/**
  * Created by fide on 17.04.17.
  */
case class SenseVectorModel(
  sense_inventory: Sense.InventoryName,
  word_vector_model: WordVector.ModelName) {

  override def toString: String = {
    val word_vector_component = if (word_vector_model == WordVector.undefined) "" else s"_$word_vector_model"
    s"$sense_inventory$word_vector_component"
  }

  def toDbModelString: String = {
    val word_vector_component = if (word_vector_model == WordVector.undefined)
      s"_${WordVector.self}" else s"_$word_vector_model"

    s"$sense_inventory$word_vector_component"
  }

  def isInventoryCoSet: Boolean = Seq(Sense.cosets1k, Sense.cosets2k).contains(sense_inventory)
}

object SenseVectorModel {
  def parseFromString(model: String): SenseVectorModel = {
    model.split("_") match {
      case Array(inventory: String, word_vector_model: String) =>
        new SenseVectorModel(
          sense_inventory = Sense withName inventory,
          word_vector_model = WordVector withName word_vector_model
        )
    }
  }
}