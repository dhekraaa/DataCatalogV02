package dataProfiling

object Test {
  val path1 = "src/main/resources/sales.csv"

  def main(args: Array[String]): Unit = {
    val dataProfiling = new DataProfilingV02
    val src = dataProfiling.readData(path1,"sales")
    val res = dataProfiling.Profiler(src,"transactionId")
    println("****************test**********")
    dataProfiling.Profiler(src)
    println(res)



  }

}
