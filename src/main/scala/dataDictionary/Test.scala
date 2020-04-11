package dataDictionary


object Test {
  val path = "src/main/resources/customers.csv"
  val path1 = "src/main/resources/sales.csv"

  def main(args: Array[String]): Unit = {
    //instantiate Our Data Dictionary
    val dictionary = new DataDictionaryV02

    //reading data
    dictionary.readData(path,"customers")
    dictionary.readData(path1,"sales")

    //testing methods
    dictionary.getDataBaseInfo(path)
    dictionary.getTablesInfo(path)
    dictionary.getTableInfo("sales")
    dictionary.getFunctionsList()
    Thread.sleep(100000)


  }

}
