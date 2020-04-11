package dataDictionary

import org.apache.spark.sql.SparkSession

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
    catalog.listTables().select("name", "description", "isTemporary", "tableType").show()
  }

  //method returns Table Details
  def getTableInfo(ResourceName: String):Unit={
    catalog.listColumns(ResourceName).show()
  }

  //Returns a list of functions registered in the current database.
  def getFunctionsList():Unit={
    catalog.listFunctions().select("name","description","className","isTemporary").show(10)
  }
  //it's possible to add a parameter of db name

 // Thread.sleep(1000000)

  }
