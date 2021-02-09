package dto

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, dayofmonth, hour, month, year}
import org.apache.spark.sql.types.TimestampType

object EscaleDto {
  def chaining(dataFrame: DataFrame): DataFrame = {
    dataFrame.withColumn("device_sent_timestamp", col("device_sent_timestamp").cast(TimestampType))
      .withColumn("year", year(col("device_sent_timestamp")))
      .withColumn("month", month(col("device_sent_timestamp")))
      .withColumn("day", dayofmonth(col("device_sent_timestamp")))
      .withColumn("hour", hour(col("device_sent_timestamp")))
  }

}
