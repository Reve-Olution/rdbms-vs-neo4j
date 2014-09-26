package ch.waterbead.sqlloader

import ch.waterbead.config.Config
import ch.waterbead.domain.Group
import groovy.sql.Sql

class GroupSQLLoader extends SQLLoader {
	private static final SQL = "INSERT INTO GROUPES (id, nom) VALUES (:id, :nom)"
        
    def load(List<Group> groups) {
        sql.withBatch(SQL) {
            ps ->
            groups.each() {
                Group g ->
                ps.addBatch(id : g.id, nom : g.name)
            }
        }
    }
}

