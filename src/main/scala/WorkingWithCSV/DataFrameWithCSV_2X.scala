package WorkingWithCSV

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object DataFrameWithCSV_2X extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)

  /**
   * With Spark 2.0 SparkSession became an entry point for programming with DataFrame and Dataset.
   * Creating Spark Session and RDD in Spark 2x style
   */
  val spark = SparkSession.builder()
    .appName("DataFrameWithCSV_2X")
    .master("local[*]")
    .getOrCreate()

  /**
   * In Spark 2.0 We can directly read CSV file
   *
   * .option is used to specify whether our CSV file have HEADER
   * In .option we can also inferSchema that is to extract the exact Data Type in CSV
   * .options can be used to pass multiple option
   *
   * val properties = Map("header" -> "true", "inferSchema" -> "true")
   * options(properties)
   */
  val df = spark.read
//    .option("header", "true")
//    .option("inferSchema", "true")
    .options(Map("header" -> "true", "inferSchema" -> "true"))
    .csv("/home/dell/udemy/learning_scala/dataset/tips.csv")

  /**
   * To print schema of dataframe
   */
  df.printSchema()

  /**
   * To print the contents of dataframe
   */
  df.show()


}
