package business.contract

import java.util

import org.apache.spark.sql.DataFrame

trait EscaleContract {
  def deviceFamilyInfo(df: DataFrame): util.List[Map[String, Any]]

  def osFamilyInfo(df: DataFrame): java.util.List[Map[String, Any]]

  def browserFamilyInfo(df: DataFrame): java.util.List[Map[String, Any]]
}