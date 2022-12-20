package RDD

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object SparkSession_2X extends App {

  /**
   * As Spark throws too many INFO and WARNING logs
   * we will try to catch most of the INFO and WARNING
   * and pass only ERROR logs to console using log4j
   */
  Logger.getLogger("org").setLevel(Level.ERROR)


  /**
   * With Spark 2.0 SparkSession became an entry point for programming with DataFrame and Dataset.
   * Creating Spark Session and RDD in Spark 2x style
   */
  val spark = SparkSession.builder()
    .appName("SparkSession Application")
    .master("local[*]")
    .getOrCreate()
  val array = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
  val arrayRDD = spark.sparkContext.parallelize(array)
  println("Number of elements in RDD: ", arrayRDD.count())
  arrayRDD.foreach(println)

  val file = spark.sparkContext.textFile("/home/dell/udemy/dataset/wiki.txt")
  val fileRDD = file.count()
  println(s"Number of lines in the sample text file is: $fileRDD")
  file.take(20).foreach(println)

}
