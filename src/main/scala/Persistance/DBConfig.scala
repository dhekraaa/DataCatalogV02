package Persistance

import java.sql.{Connection, DriverManager}
import java.util.Properties

object DBConfig {
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

  var  conn : Connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)


    }
