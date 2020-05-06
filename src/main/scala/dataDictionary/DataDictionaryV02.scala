package dataDictionary

import Persistance.DBConfig
import org.apache.spark.sql.{SaveMode, SparkSession}

class DataDictionaryV02 {
  //create a spark Session
  val spark = SparkSession.builder
    .master("local[*]")
    .appName("DataCatalog")
    .getOrCreate()

  //interacting with Catalog API
  val catalog = spark.catalog

  //read resources files:
  def readData(path:String, ResourceName: String): Unit ={
    val df = spark.read.option("header",true).option("description", true).csv(path) //.format(path)
    df.createTempView(ResourceName)

  }

  //method which returns databases information
  def getDataBaseInfo(path: String): Unit = {

    catalog.listDatabases().select("name").show()
  }

  //method which returns all tables information
  def getTablesInfo(path: String): Unit ={
    catalog.listTables().select("name", "description", "isTemporary", "tableType").write.mode(SaveMode.Overwrite).jdbc(DBConfig.jdbcUrl,"TablesList",DBConfig.connectionProperties)//.show()
  }

  //method returns Table Details
  //todo remove hard code Table Name
  //todo paramaterize url in jdbc
  def getTableInfo(ResourceName: String):Unit={
    catalog.listColumns(ResourceName).write.mode(SaveMode.Overwrite).jdbc(DBConfig.jdbcUrl,"TablesInfo",DBConfig.connectionProperties) //.show()
  }

  //Returns a list of functions registered in the current database.
  def getFunctionsList():Unit={
    catalog.listFunctions().select("name","description","className","isTemporary").write.mode(SaveMode.Overwrite).jdbc(DBConfig.jdbcUrl,"TablesInfo",DBConfig.connectionProperties) //.show(10)
  }
  //it's possible to add a parameter of db name

 // Thread.sleep(1000000)

  }
