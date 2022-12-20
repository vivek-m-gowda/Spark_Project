import org.apache.spark._
import org.apache.log4j._


Logger.getLogger("org").setLevel(Level.ERROR)

val sc = new SparkContext("local[*]", "LearningRDD")
val intArray = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
/*
The sc. parallelize() method is the SparkContext's parallelize method to create a parallelized collection.
This allows Spark to distribute the data across multiple nodes,
instead of depending on a single node to process the data
*/
//create RDD
val intRDD = sc.parallelize(intArray)

intRDD.first()
intRDD.take(5)
intRDD.count()
intRDD.collect().foreach(println)

//to check the RDD partition size
intRDD.partitions.size


//To manually partition size
val intList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
val listRDD = sc.parallelize(intList, 6)
listRDD.partitions.size

//to read a file using RDD
val fileRDD = sc.textFile("/home/dell/udemy/dataset/wiki.txt")
fileRDD.first()
fileRDD.partitions.size
fileRDD.count()
val uppercase = fileRDD.map(_.toUpperCase())
uppercase.collect()

//RDD Transformations
val data = Array(
  "Hello there",
  "Welcome to spark-scala course",
  "This is all about Spark RDD",
  "My name is vivek",
  "Hava a nice day"
)
val dataRDD = sc.parallelize(data)

//Transforming the data using filter
val filterRDD = dataRDD.filter(line => line.length > 15)
filterRDD.collect()
filterRDD.collect.foreach(println)

//Transforming the data using map
val mapRDD = dataRDD.map(line => line.split(" "))
mapRDD.collect()

val LowerCaseRDD = dataRDD.map(line => line.toLowerCase())
LowerCaseRDD.collect()

val UpperCaseRDD = LowerCaseRDD.map(_.toUpperCase())
UpperCaseRDD.collect()

//Transforming the data using flatmap
/*
flatMap() method is identical to the map() method,
but the only difference is that in flatMap the inner
grouping of an item is removed and a sequence is generated.
 */
val flatmapRDD = dataRDD.flatMap(line => line.split(" "))
flatmapRDD.collect()

//Transforming the data using distinct
val numArray = Array(1,2,2,3,3,5,4,4,8,8,1,5,3,2,3)
val numRDD = sc.parallelize(numArray)
val distinctElementRDD = numRDD.distinct()
distinctElementRDD.collect()


