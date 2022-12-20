package WorkingWithCSV

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object DataFrameWithCSV_1X extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)

  val sparkConf = new SparkConf()
  sparkConf.setAppName("DataFrameWithCSV_1X")
  sparkConf.setMaster("local[*]")

  /**
   * In Spark 1X Data frame is created using SQL Context
   * and SQL Context is created using Spark Context
   */
  val sc = new SparkContext(sparkConf)
  val sqlContext = new SQLContext(sc)

  /**
   * Spark 1X don't directly support data frames
   * In order to use DF in 1X we should add a databricks or tototoshi dependency
   * .option is used to specify whether our CSV file have HEADER
   * In .option we can also inferSchema that is to extract the exact Data Type in CSV
   */
  val df = sqlContext.read
    .option("header","true")
    .option("inferSchema", "true")
    .format("com.databricks.spark.csv")
    .load("/home/dell/udemy/learning_scala/dataset/tips.csv")

  /**
   * To print schema of dataframe
   */
  df.printSchema()

  /**
   * To print the contents of dataframe
   */
  df.show()

}
