package service

import business.EscaleBusiness
import config.Environment
import dao.EscaleDao
import dto.EscaleDto
import model.EscaleModel.Field
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{DataFrame, SparkSession}
import service.contract.ServicesOperations
import service.enums.TypeFilter
import utils.Utils

object EscaleServiceImpl extends ServicesOperations {
  var dfMean: DataFrame = null
  val spark = sparkContextInitialize()
  val escaleDao = new EscaleDao(spark)

  private def sparkContextInitialize(): SparkSession = {
    SparkSession.builder
      .master("local")
      .appName("Escale_test")
      .config("spark.some.config.option", true).getOrCreate()
  }

  def sourceDataHandler(): Unit = {
    if (!Utils.isSourceFolderEmpty(Environment.getJsonSourceFolder())) {
      dfMean = escaleDao.loadJsonFile().transform(EscaleDto.chaining).repartition(col(Field.YEAR), col(Field.MONTH), col(Field.DAY)).cache()
    } else {
      throw new Exception("Json File not Found")
    }
  }

  override def searchSessionizationData(typeFilter: TypeFilter): String = {

    var json = ""

    typeFilter match {
      case TypeFilter.Browser => {
        json = parse2Json(EscaleBusiness.browserFamilyInfo(dfMean), Field.DAY, Field.BROWSER_FAMILY, Field.MEDIAN)
      };
      case TypeFilter.Device => {
        json = parse2Json(EscaleBusiness.deviceFamilyInfo(dfMean), Field.DAY, Field.DEVICE_FAMILY, Field.MEDIAN)
      };
      case TypeFilter.OS => {
        json = parse2Json(EscaleBusiness.osFamilyInfo(dfMean), Field.DAY, Field.OS_FAMILY, Field.MEDIAN)
      };
    }

    json
  }
}
