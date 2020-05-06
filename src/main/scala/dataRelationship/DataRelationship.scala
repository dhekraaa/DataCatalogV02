package dataRelationship

import org.apache.spark.sql.SparkSession

object DataRelationship {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("DataCatalog")
      //.config("spark.sql.warehouse.dir", warehouseLocation)
      //.enableHiveSupport()
      .getOrCreate()

    //interacting with catalog
    //val catalog = spark.catalog



  }

}
