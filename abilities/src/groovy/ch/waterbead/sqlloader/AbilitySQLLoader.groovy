package ch.waterbead.sqlloader

import ch.waterbead.config.Config
import ch.waterbead.domain.Ability
import groovy.sql.Sql

class AbilitySQLLoader extends SQLLoader {
    private static final SQL_COMPETENCES = "INSERT INTO COMPETENCES (id, nom) VALUES (:id, :nom)";
    
    def load(List<Ability> abilities) {
        sql.withBatch(SQL_COMPETENCES) {
            ps ->
            abilities.each() {
                Ability a ->
                ps.addBatch(id : a.id, nom : a.name)
            }
        }
    }
}

