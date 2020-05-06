package dataProfiling

import org.apache.spark.sql.SparkSession

object DataProfiling {
  def main(args: Array[String]): Unit = {


    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("DataCatalog")
      .getOrCreate()
  //read data
    val df2 = spark.read.option("description",true).option("header", true).csv("src/main/resources/sales.csv") //==>je dois spécifier
    
    df2.createTempView("sales")
    val test = df2.describe()
    test.show()

  }


  }
