package dataDictionary

import org.apache.spark.sql.SparkSession


object DataDictionary {
  def main(args: Array[String]): Unit = {

   //val warehouseLocation = new File("c:/test").getAbsolutePath
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("DataCatalog")
      //.config("spark.sql.warehouse.dir", warehouseLocation)
     //.enableHiveSupport()
      .getOrCreate()



    //read from csv files
    println("********************************reading CSV Files********************************")
    val df = spark.read.csv("src/main/resources/customers.csv")
    df.createTempView("customers")

    val df2 = spark.read.option("description",true).option("header", true).csv("src/main/resources/sales.csv") //==>je dois spécifier
    df2.createTempView("sales")

    val df3 = spark.read.csv("src/main/resources/eventimedata.md")
    df3.createTempView("eventimedata")

    val df4 = spark.read.csv("src/main/resources/banque.sql")
    df4.createTempView("banque")

    println("******************************** 1) interacting with catalog********************************")
    //interacting with catalog
    val catalog = spark.catalog
    println("********************************   A)Resources Dictionary********************************")
  //Returns a list of functions registered in the current database.
    catalog.listFunctions().select("name","description","className","isTemporary").show(10)

    //print the databases
    println("********************************   A)Data Bases Dictionary********************************")

    catalog.listDatabases().select("name").show()
    println("********************************   A)Sales Csv file Dictionary********************************")

    catalog.listColumns("sales").select("name").show()
    catalog.listTables().select("name", "description", "isTemporary", "tableType").show()

    println(spark.catalog.listColumns("sales").head())


   println("********************************   A)Sales Dictionary with hive table ********************************")
    ///testing hive tables////////////////////////////////////////
    //import spark.sql
    //sql("CREATE TABLE hive_records(key int, value string) STORED AS PARQUET")

    catalog.listColumns("sales").show()
   // catalog.listColumns("sales").write.mode(SaveMode.Overwrite).saveAsTable("hive_records")
   // sql("SELECT * FROM hive_records").show()
   //////////////////////////////////////////////////////////



    println("********************************   A)Banque file Dictionary********************************")

    catalog.listColumns("banque").show()
    Thread.sleep(100000)


  }

}
