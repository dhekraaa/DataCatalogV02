package JustForTest
import org.apache.spark.sql.SparkSession

object Test {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("DataCatalog")
      //.config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()

    //val hive = HiveWarehouseSession.session(spark).build()

    val df2 = spark.read.option("description",true).option("header", true).csv("src/main/resources/sales.csv")
    //interacting with catalog
    val catalog = spark.catalog
    catalog.listColumns("sales").show()
    val data = catalog.listColumns("sales")
    data.createOrReplaceGlobalTempView("mytempTable")
    //spark.sql("create table mytable as select * from mytempTable")
    catalog.listTables().select("name", "description", "isTemporary", "tableType").show()
  }

}
