package RDD

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}


object SparkContext_1X extends App{

  /**
   * As Spark throws too many INFO and WARNING logs
   * we will try to catch most of the INFO and WARNING
   * and pass only ERROR logs to console using log4j
   */
  Logger.getLogger("org").setLevel(Level.ERROR)


  /**
   * In earlier versions of Spark,
   * SparkContext was an entry point for programming with RDD and connecting to Spark Cluster.
   *
   * Creating Spark Context and RDD in Spark 1x style
   */
  val sparkConf = new SparkConf()
  sparkConf.setAppName("SparkContext Application")
  sparkConf.setMaster("local[*]")
  val sc = new SparkContext(sparkConf)

  /**
   * We can also create the SparkContext by
   * val sc = new SparkContext("local[*]", "First Spark Application")
   */

  val array = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
  val arrayRDD = sc.parallelize(array,2)
  println("Number of elements in RDD: ", arrayRDD.count())
  arrayRDD.foreach(println)

  val file= sc.textFile("/home/dell/udemy/dataset/wiki.txt")
  val fileRDD = file.count()
  println(s"Number of lines in the sample text file is: $fileRDD")

}
