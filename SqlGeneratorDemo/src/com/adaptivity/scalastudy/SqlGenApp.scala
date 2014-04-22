package com.adaptivity.scalastudy

object SqlGenApp extends App {

  override def main(args : Array[String]) : Unit = {
    
    println("Let's do Insert First ----------")
    val e = InsertSpec("testTable", "First|Second|Third",Seq("1|2|3","4|5|6"))
    println(SqlStringGenerator.generateSql(e))
    
    println("Let's do Update Next ----------")
    val u = UpdateSpec("ice_cream",
    				fields = "First|Second|Third",
    				rows = Seq("1|2|3","4|5|6"),
    				Map("First" -> "Hello", "Second" -> "GoodBye")
    )
    println(SqlStringGenerator.generateSql(u))
    
    val e2 = e.copy(table = "testTable2")
    println(SqlStringGenerator.generateSql(e2))
    
  }
}