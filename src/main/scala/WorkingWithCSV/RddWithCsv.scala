package WorkingWithCSV

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object RddWithCsv extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark = SparkSession.builder()
    .appName("RDD with CSV")
    .master("local[*]")
    .getOrCreate()

  val csvRDD = spark.sparkContext.textFile("/home/dell/udemy/dataset/tips.csv")
  println(csvRDD.count)

  /**
   * To Print First line
   */
  val header = csvRDD.first()
  println(header)

  /**
   * To Remove header line
   */
  val csvWithOutHeader = csvRDD.filter(_ != header)
  //OR
  //val csvWithOutHeader = csvRDD.filter(line => line != header)
  csvWithOutHeader.take(10).foreach(println)

  /**
   * To find the Total revenue generated
   */
  val separateBillColumn = csvWithOutHeader.map(x => x.split(",")(0)) //Transformation
  val convertStringtoDouble = separateBillColumn.map(_ toDouble)
  val totalRevenue = convertStringtoDouble.sum()
  println(s"Total revenue generated is = $totalRevenue")

  /**
   * To find the Total Tips Received
   */
  val separateTipsColumn = csvWithOutHeader.map(x => x.split(",")(1))
  val convertStringtoDouble1 = separateTipsColumn.map(_ toDouble)
  val totalTipsReceived = convertStringtoDouble1.sum()
  println(s"Total Tips Received  is = $totalTipsReceived")

  //val totalTipsRecived = separateTipsColumn.countByValue()
  //val sortedResults = totalTipsReceived.toSeq.sortBy(_._1)
  //sortedResults.foreach(println)

  /**
   * A RDD with limited columns
   */
  val csvWithLimitedColumns = csvWithOutHeader.map(line => {
    //(line.split(", ")(2), line.split(", ")(3)) OR
    val colArray = line.split(",")
    (colArray(2), colArray(3))
    //List(colArray(2), colArray(3)) //can pass as list or array
    //Array(colArray(2), colArray(3)).mkString(" ") //can pass as list or array
  })
  csvWithLimitedColumns.take(10).foreach(println)

  /**
   * To save a Limited columns CSV as a text file
   */
  //csvWithLimitedColumns.saveAsTextFile("/output/file")

}
