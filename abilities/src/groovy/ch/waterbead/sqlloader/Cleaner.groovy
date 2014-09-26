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
        SQLUtil.dropTables(sql, tablesToClean)
        SQLUtil.runScript(sql, FILE_TABLES)
    }
    
    def static addConstraints() {
        Sql sql = ConnectionManager.getSql()
        SQLUtil.runScript(sql, FILE_CONSTRAINTS)
    }
}