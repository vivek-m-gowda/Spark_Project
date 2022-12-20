
import org.apache.spark._
import org.apache.log4j._

object SparkRDD extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)

  val sc = new SparkContext("local[*]", "LearningRDD" )
  val intArray = Array(1,2,3,4,5,6,7,8,9,0)
  //create RDD
  val intRDD = sc.parallelize(intArray)

  println(intRDD.first())
  intRDD.take(5).foreach(println)
  println(intRDD.count())
  intRDD.collect().foreach(println)

  //to check the RDD partition size
  intRDD.partitions.size

}
