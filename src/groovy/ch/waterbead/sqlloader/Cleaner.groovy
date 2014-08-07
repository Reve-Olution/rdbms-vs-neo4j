package ch.waterbead.sqlloader

import ch.waterbead.config.Config
import groovy.sql.Sql
import org.apache.ibatis.jdbc.ScriptRunner

class Cleaner  {
        def static tablesToClean = ["EMPLOYES_GROUPES","EMPLOYES_COMPETENCES","EMPLOYES_PROJETS","EMPLOYES","GROUPES","COMPETENCES","PROJETS"]
	def static clean() {
        Sql sql = ConnectionManager.getSql()
            
        if(Config.DEBUG) {
            println("Cleaning tables")
        }
        
        tablesToClean.each() {
            table->
            sql.execute("DROP TABLE " + table)
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

