package Persistance
import java.util.Properties
import java.sql.DriverManager
import java.sql.Connection


object DBConnection {
  val jdbcHostname = "localhost"
  val jdbcPort = 3306
  val jdbcDatabase = "datacatalog"
  val jdbcUsername = "root"
  val jdbcPassword = ""
  val jdbcUrl = s"jdbc:mysql://${jdbcHostname}:${jdbcPort}/${jdbcDatabase}?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
  // Create a Properties() object to hold the parameters.
  val connectionProperties = new Properties()

  connectionProperties.put("user", s"${jdbcUsername}")
  connectionProperties.put("password", s"${jdbcPassword}")
   var  conn : Connection = null

  private def DBConnection() = ???

  def  getConnection() : Connection = {

      if (conn == null){
        Class.forName("com.mysql.cj.jdbc.Driver")
        //conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)
         conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)



    }
    return conn


  }

}
