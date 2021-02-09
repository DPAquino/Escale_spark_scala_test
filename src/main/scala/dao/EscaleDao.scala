package dao

import config.Environment
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.TimestampType
import org.apache.spark.sql.{DataFrame, SparkSession}

class EscaleDao(sparkSession: SparkSession) {

  def loadJsonFile(): DataFrame = {
    sparkSession.read.format("json").json(Environment.getJsonSourceFolder())
      .withColumn("device_sent_timestamp", col("device_sent_timestamp").cast(TimestampType))
      .withColumn("year", year(col("device_sent_timestamp")))
      .withColumn("month", month(col("device_sent_timestamp")))
      .withColumn("day", dayofmonth(col("device_sent_timestamp")))
      .withColumn("hour", hour(col("device_sent_timestamp")))
  }
}
