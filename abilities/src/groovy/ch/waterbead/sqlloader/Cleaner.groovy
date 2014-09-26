package ch.waterbead.sqlloader

import ch.waterbead.config.Config
import groovy.sql.Sql
import java.sql.SQLException
import org.apache.ibatis.jdbc.ScriptRunner

class Cleaner  {
    def static final FILE_TABLES = "rdbms/tables"
    def static final FILE_CONSTRAINTS = "rdbms/constraints"
    
    def static tablesToClean = ["EMPLOYES_GROUPES","EMPLOYES_COMPETENCES","EMPLOYES_PROJETS","EMPLOYES","GROUPES","COMPETENCES","PROJETS"]
    def static clean() {
        Sql sql = ConnectionManager.getSql()
            
        tablesToClean.each() {
            table->
            try {
                sql.execute("DROP TABLE " + table)
            } catch(SQLException ex) {
                println ex.message;
            }
        }
        
        executeScript(sql, FILE_TABLES);
    }
    
    def static addConstraints() {
        Sql sql = ConnectionManager.getSql()
        executeScript(sql, FILE_CONSTRAINTS)
    }
    
    def static executeScript(Sql sql, String fileName) {
        ScriptRunner sr = new ScriptRunner(sql.connection);
        def file = ClassLoader.getSystemResource(fileName)
        file.withReader() {
            reader -> sr.runScript(reader);
        }
    }
}

