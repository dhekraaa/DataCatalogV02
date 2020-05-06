package DataProfiling

import io.netty.handler.codec.http2.DefaultHttp2DataFrame
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, DataFrameReader, Row, SparkSession}

class DataProfilingV02 {
  val spark = SparkSession.builder
    .master("local[*]")
    .appName("DataCatalog")
    .getOrCreate()


  import spark.implicits._

  val schema = StructType(
    StructField("firstName", StringType, true) ::
      StructField("lastName", IntegerType, false) ::
      StructField("middleName", IntegerType, false) :: Nil)

  /*def  readData(path:String, ResourceName: String): DataFrame =
  {
    //val df = spark.read.option("header", true).option("description", true).format(path) :DataFrame
    //val df = spark.createDataFrame(spark.sparkContext.emptyRDD[Row], schema)
    val df = spark.read.option("header", true).option("description", true).format(path)//.csv(path) //.format(path)

    //df.createTempView(ResourceName)
    return df
  }*/
  def readData(path:String, ResourceName: String): DataFrame ={
    val df = spark.read.option("header",true).option("description", true).csv(path) //.format(path)
    //df.toDF()
    //val dfWithoutSchema = spark.createDataFrame(df)
    return  df.toDF()

  }

  def Profiler(data: DataFrame, columns: String*): Unit ={
   // val dfWithoutSchema = spark.createDataFrame(data)
    data.describe().show()
    //val output = data.describe()
      //return output

  }


}
