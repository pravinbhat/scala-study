package com.adaptivity.scalastudy

object SqlStringGenerator {

  def generateSql(spec : SqlSpec) : String = {
    
    // As we saw, this did not work to extract the values.
    //         val (table, method, fields, rows) = spec
    // The correct expression follows. You need to specify the class name 
    // in the extractor. Then you can extract the individual fields.
    //         val SqlSpec(table, method, fields, rows) = spec
    
    spec match {
      case SqlSpec(table, "insert",fields, rows, _ ) => 
        val f = stringSeparate(fields)
        val r : Seq[Seq[String]] = rows.map((e : String) => stringSeparate(e))
        	// Now create final result and return value
        "insert into " + table + " ( " + f.mkString(",") + " ) values " + 
        		r.map((ss : Seq[String]) => ss.mkString("(", ",", ")" )).mkString(",")
        		
      case SqlSpec(table, "update", fields, rows, where) =>
        val whereStr = where map ( x  => x._1 + "='" + x._2 + "'") mkString(" and ")
        
        val f = stringSeparate(fields)
        val r : Seq[Seq[String]] = rows.map((e : String) => stringSeparate(e))
        val oneRow : Option[Seq[String]] = r.headOption
          // Create final result and return value
        oneRow match {
          case Some(row) => 
            // update tableName set field=value, field2=value where field3=value3
          "update " + table + " set " + (f zip row map (x => x._1 + "=" + x._2) mkString(",")) + " where " + whereStr
          case None => "Sorry, I didn't get any values"
        }
        		
      case _ => spec.toString + " did not match anything"
    }
    
    
  }
  
  // Utility method to separate a string into a Seq of String
  def stringSeparate(str : String) : Seq[String] = {
    str.split("\\|")
  }
  
}
/**
 * fields: Bar delimited list of field names.
 * rows: Seq(Bar delimited list of string values)
 */
case class SqlSpec(table : String = "defaultTable", method : String, fields : String, rows : Seq[String], where : Map[String,String] = Map.empty )




