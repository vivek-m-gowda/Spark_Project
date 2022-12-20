package DataFrame

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types.{IntegerType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object DataFrame_1X extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)

  /**
   * In earlier versions of Spark,
   * SparkContext was an entry point for programming with RDD and connecting to Spark Cluster.
   *
   * Creating Spark Context and RDD in Spark 1x style
   */
  val sparkConf = new SparkConf()
  sparkConf.setAppName("DataFrame_1X")
  sparkConf.setMaster("local[*]")
  val sc = new SparkContext(sparkConf)

  /**
   * In Spark 1X Data frame is created using SQL Context
   * and SQL Context is created using Spark Context
   */
  val sqlContext = new SQLContext(sc)

  val rdd = sc.parallelize(Array(1,2,3,4,5,6,7,8,9,0))

  val schema = StructType (
    StructField("Number", IntegerType, false) :: Nil
  )

  /**
   * Dataframes should be a collection of rows
   */
  val rowRDD = rdd.map(line => Row(line))

  /**
   * Creating Dataframes
   */
  val df = sqlContext.createDataFrame(rowRDD, schema)

  /**
   * To print schema of dataframe
   */
  df.printSchema()

  /**
   * To print the contents of dataframe
   */
  df.show()

}
