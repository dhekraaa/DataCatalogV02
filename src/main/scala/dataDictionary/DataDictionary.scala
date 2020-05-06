package dataDictionary

import java.lang
import java.sql.Statement

import Persistance.{DBConfig, DBConnection}
import com.mysql.jdbc.Driver
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

  /* var conn = DBConnection.getConnection()

   var statement : Statement  = conn.createStatement()*/


   catalog.listDatabases().describe()
   catalog.listDatabases().describe().write.jdbc(DBConfig.jdbcUrl,"DatabasesList",DBConfig.connectionProperties)
    //print the databases
    println("********************************   A)Data Bases Dictionary********************************")

    catalog.listDatabases().select("name").show()
    println("********************************   A)Sales Csv file Dictionary********************************")

    catalog.listColumns("sales").select("name").show()
    catalog.listTables().select("name", "description", "isTemporary", "tableType").show()

    println(spark.catalog.listColumns("sales").head())
/////////////////////////////////////////////////////////////////////
   println("********************************   test connection with data base ********************************")


  // Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver")

   //Class.forName("org.mariadb.jdbc.Driver")
   //Class.forName("com.mysql.cj.jdbc.Driver")
  Class.forName("com.mysql.cj.jdbc.Driver")
   val jdbcHostname = "localhost"
   val jdbcPort = 3306
   val jdbcDatabase = "datacatalog"
   val jdbcUsername = "root"
   val jdbcPassword = ""


   // Create the JDBC URL without passing in the user and password parameters.
   val jdbcUrl = s"jdbc:mysql://${jdbcHostname}:${jdbcPort}/${jdbcDatabase}?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"


   // Create a Properties() object to hold the parameters.
   import java.util.Properties
   val connectionProperties = new Properties()

   connectionProperties.put("user", s"${jdbcUsername}")
   connectionProperties.put("password", s"${jdbcPassword}")
   import java.sql.DriverManager
   val connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)
   connection.isClosed()
   ///////////////////////////////////////////////////////////////////////
   println("********************************   A)Sales Dictionary with hive table ********************************")
    ///testing hive tables////////////////////////////////////////
    //import spark.sql
    //sql("CREATE TABLE hive_records(key int, value string) STORED AS PARQUET")

    catalog.listColumns("sales").show()
   catalog.listColumns("sales").write
     .jdbc(jdbcUrl, "SalesColumns", connectionProperties)
   /*test save as table
   println("*******************test save as table*******************")
    catalog.listColumns("sales").toDF().write.saveAsTable("salesTest" : String)*/
   // catalog.listColumns("sales").write.mode(SaveMode.Overwrite).saveAsTable("hive_records")
   // sql("SELECT * FROM hive_records").show()
   //////////////////////////////////////////////////////////



    println("********************************   A)Banque file Dictionary********************************")

    catalog.listColumns("banque").show()
   // Thread.sleep(100000)

   /*val createTableHql : String = s"CREATE TABLE upc_hbt2(key string, value string)"+
     "STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'"+
     "WITH SERDEPROPERTIES ('hbase.columns.mapping' = ':key,value:value')"+
     "TBLPROPERTIES ('hbase.table.name' = 'upc_hbt2')"

   spark.sql(createTableHql)*/

   Thread.sleep(100000)


  }

}
