package ch.waterbead.sqlloader

import ch.waterbead.config.Config
import ch.waterbead.domain.Project
import groovy.sql.Sql

class ProjectSQLLoader extends SQLLoader {
	private static final SQL = "INSERT INTO PROJETS (id, nom) VALUES (:id, :nom)"
        
    def load(List<Project> projects) {
        sql.withBatch(SQL) {
            ps ->
            projects.each() {
                Project p ->
                if(Config.DEBUG) {
                    println p.id + " " + p.name
                }
                ps.addBatch(id : p.id, nom : p.name)
            }
        }
    }
}

