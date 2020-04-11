package dataDictionary

import org.apache.spark.sql.SparkSession

object TestingHiveTables {
  def main(args: Array[String]): Unit = {
   // val warehouseLocation = new File("C:/spark-warehouse").getAbsolutePath

   val spark = SparkSession
      .builder()
      .appName("Data Catalog")
      .config("spark.master", "local")
      .enableHiveSupport()
      .getOrCreate()

    val salesTableDetails = spark.read
      .option("header", "true")
      .csv("src/main/resources/sales.csv")

    /* val spark = SparkSession.builder
      .master("local")
      .appName("DataCatalog")
      .enableHiveSupport()
      .getOrCreate()*/
   /* sql("CREATE TABLE hive_records(key int, value string) STORED AS PARQUET")
    val catalog = spark.catalog
    catalog.listColumns("sales").show()
    catalog.listColumns("sales").write.mode(SaveMode.Overwrite).saveAsTable("hive_records")
    sql("SELECT * FROM hive_records").show()
    //sql("CREATE TABLE hive_records(key int, value string) STORED AS PARQUET")

*/



  }

}
