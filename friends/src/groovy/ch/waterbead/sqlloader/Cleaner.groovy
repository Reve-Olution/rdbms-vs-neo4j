package ch.waterbead.sqlloader

import ch.waterbead.config.Config
import groovy.sql.Sql
import java.sql.SQLException
import org.apache.ibatis.jdbc.ScriptRunner

class Cleaner  {
    def static tablesToClean = ["AMIS","PERSONNES"]
    def static clean() {
        Sql sql = ConnectionManager.getSql()
            
        if(Config.DEBUG) {
            println("Cleaning tables")
        }
        
        tablesToClean.each() {
            table->
            try {
                sql.execute("DROP TABLE " + table)
            } catch(SQLException ex) {
                println ex.message;
            }
        }
            
        ScriptRunner sr = new ScriptRunner(sql.connection);
        def file = ClassLoader.getSystemResource("rdbms/ddl")
        file.withReader() {
            reader -> sr.runScript(reader);
        }
            
        if(Config.DEBUG) {
            println("Tables cleaned")
        }
    }
}

