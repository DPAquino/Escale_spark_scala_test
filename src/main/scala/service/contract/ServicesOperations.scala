package service.contract

import java.util

import service.enums.TypeFilter

trait ServicesOperations {

  def searchSessionizationData(typeOperation: TypeFilter): String

  def parse2Json(source: util.List[Map[String, Any]], customFieldA: String, customFieldB: String, customFieldC: String): String = {

    var parsed: String = "["
    for (i <- 1 to source.size()) {

      val fieldA = source.get(i - 1).get(customFieldA).getOrElse()
      val fieldB = source.get(i - 1).get(customFieldB).getOrElse()
      val fieldC = source.get(i - 1).get(customFieldC).getOrElse()

      parsed = parsed.concat("""{"%s":%s,%s},""".format(fieldA, fieldB, fieldC))
    }
    parsed = parsed.dropRight(1) + "]"

    return parsed
  }
}
