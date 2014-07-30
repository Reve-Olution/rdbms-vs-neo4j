package ch.waterbead.sqlloader

import groovy.sql.Sql

class Cleaner  {
        def static tablesToClean = ["EMPLOYES_GROUPES","EMPLOYES_COMPETENCES","EMPLOYES_PROJETS","EMPLOYES","GROUPES","COMPETENCES","PROJETS"]
	def static clean() {
            Sql sql = ConnectionManager.getSql()
            tablesToClean.each() {
                table->
                sql.execute("DELETE FROM " + table)
            }
    }
}

