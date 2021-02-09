package business

import java.util

import business.contract.EscaleContract
import model.EscaleModel.Field
import org.apache.spark.sql.catalyst.expressions.aggregate.ApproximatePercentile
import org.apache.spark.sql.functions.{col, lit}
import org.apache.spark.sql.{Column, DataFrame}


object EscaleBusiness extends EscaleContract {
  def browserFamilyInfo(df: DataFrame): util.List[Map[String, Any]] = {
    implicit val mapEncoder = org.apache.spark.sql.Encoders.kryo[Map[String, Any]]
    val dfB = df.groupBy(col(Field.DAY), col(Field.BROWSER_FAMILY))
      .agg(percentile_approx(col(Field.HOUR), lit(0.5)).alias(Field.MEDIAN))

    dfB.map(result => result.getValuesMap[Any](List(Field.DAY, Field.BROWSER_FAMILY, Field.MEDIAN))).collectAsList()
  }

  override def deviceFamilyInfo(df: DataFrame): util.List[Map[String, Any]] = {
    implicit val mapEncoder = org.apache.spark.sql.Encoders.kryo[Map[String, Any]]
    val dfB = df.groupBy(col(Field.DAY), col(Field.DEVICE_FAMILY))
      .agg(percentile_approx(col(Field.HOUR), lit(0.5)).alias(Field.MEDIAN))

    dfB.map(result => result.getValuesMap[Any](List(Field.DAY, Field.DEVICE_FAMILY, Field.MEDIAN))).collectAsList()
  }

  override def osFamilyInfo(df: DataFrame): util.List[Map[String, Any]] = {
    implicit val mapEncoder = org.apache.spark.sql.Encoders.kryo[Map[String, Any]]
    val dfB = df.groupBy(col(Field.DAY), col(Field.OS_FAMILY))
      .agg(percentile_approx(col(Field.HOUR), lit(0.5)).alias(Field.MEDIAN))

    dfB.map(result => result.getValuesMap[Any](List(Field.DAY, Field.OS_FAMILY, Field.MEDIAN))).collectAsList()
  }

  def percentile_approx(col: Column, percentage: Column, accuracy: Column): Column = {
    val expr = new ApproximatePercentile(
      col.expr, percentage.expr, accuracy.expr
    ).toAggregateExpression
    new Column(expr)
  }

  def percentile_approx(col: Column, percentage: Column): Column = percentile_approx(
    col, percentage, lit(ApproximatePercentile.DEFAULT_PERCENTILE_ACCURACY)
  )
}