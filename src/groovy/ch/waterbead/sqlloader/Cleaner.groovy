package ch.waterbead.sqlloader

import ch.waterbead.config.Config
import groovy.sql.Sql

class Cleaner  {
        def static tablesToClean = ["EMPLOYES_GROUPES","EMPLOYES_COMPETENCES","EMPLOYES_PROJETS","EMPLOYES","GROUPES","COMPETENCES","PROJETS"]
	def static clean() {
            Sql sql = ConnectionManager.getSql()
            
        if(Config.DEBUG) {
            println("Cleaning tables")
        }
        
        tablesToClean.each() {
            table->
            sql.execute("DELETE FROM " + table)
        }
            
        if(Config.DEBUG) {
            println("Tables cleaned")
        }
    }
}

