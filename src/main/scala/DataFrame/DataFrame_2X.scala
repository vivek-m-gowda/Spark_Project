package DataFrame

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.Row

object DataFrame_2X extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)


  /**
   * With Spark 2.0 SparkSession became an entry point for programming with DataFrame and Dataset.
   * Creating Spark Session and RDD in Spark 2x style
   */

  val spark = SparkSession.builder()
    .appName("DataFrame_2X")
    .master("local[*]")
    .getOrCreate()

  val rdd = spark.sparkContext.parallelize(Array(1,2,3,4,5,6,7,8,9,0))

  val schema = StructType(
    StructField("Numver", IntegerType, true) :: Nil
  )


  /**
   * Dataframes should be a collection of rows
   */
  val rowRDD = rdd.map(line => Row(line))

  /**
   * Creating Dataframes
   */
  val df = spark.createDataFrame(rowRDD, schema)

  /**
   * To print schema of dataframe
   */
  df.printSchema()

  /**
   * To print the contents of dataframe
   */
  df.show()

  df.show(5)
}
