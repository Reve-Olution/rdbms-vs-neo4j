package ch.waterbead.sqlloader

import groovy.sql.Sql
import java.sql.SQLException
import org.apache.ibatis.jdbc.ScriptRunner

class SQLUtil {
    def static dropTables(Sql sql, def tables) {
        println "Cleaning tables"
        
        tables.each() {
            table ->
            try {
                sql.execute("DROP TABLE " + table)
            } catch(SQLException ex) {
                println ex.message
            } 
        }
    }
    
    def static runScript(Sql sql, String fileName) {
        ScriptRunner sr = new ScriptRunner(sql.connection);
        def file = ClassLoader.getSystemResource(fileName)
        file.withReader() {
            reader -> sr.runScript(reader);
        }
    }
}

