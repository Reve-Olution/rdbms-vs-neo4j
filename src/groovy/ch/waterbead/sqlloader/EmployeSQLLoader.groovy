package ch.waterbead.sqlloader

import ch.waterbead.config.Config
import ch.waterbead.domain.Ability
import ch.waterbead.domain.Employe
import ch.waterbead.domain.Group
import ch.waterbead.domain.Project
import groovy.sql.Sql
import java.sql.Connection

class EmployeSQLLoader extends SQLLoader {
    private static final SQL_EMPLOYES = "INSERT INTO EMPLOYES (id, nom, prenom, anneenaissance) VALUES (:id, :nom, :prenom, :anneenaissance)"
    private static final SQL_EMPLOYES_COMPETENCES = "INSERT INTO EMPLOYES_COMPETENCES (id, idEmployee, idCompetence, note) VALUES (:id, :idEmploye, :idCompetence, :note)"
    private static final SQL_EMPLOYES_GROUPES = "INSERT INTO EMPLOYES_GROUPES (idEmployee, idGroupe) VALUES (:idEmployee, :idGroupe)"
    private static final SQL_EMPLOYES_PROJETS = "INSERT INTO EMPLOYES_PROJETS (idEmployee, idProjet) VALUES (:idEmployee, idProjet)"
    def load(List<Employe> people) {
        sql.withBatch(1,SQL_EMPLOYES) {
            ps ->
            people.each() {
                Employe p ->
                
                if(Config.DEBUG) {
                    println p.id + ' ' + p.lastname
                }
                ps.addBatch(id: p.id, nom: p.lastname, prenom: p.firstname, anneenaissance: p.yearOfBirthDay)

                sql.withBatch(SQL_EMPLOYES_COMPETENCES) {
                        psCompetences ->
                        p.abilities.each() {
                            Ability ability ->
                            psCompetences.addBatch(idEmploye : p.id, idCompetence: ability.id, note: ability.score)
                        }
                }
                
                sql.withBatch(SQL_EMPLOYES_PROJETS) {
                    psProjets ->
                    p.projects.each() {
                        Project project ->
                        psProjets.addBatch(idEmployee : p.id, idProjet : project.id)
                    }
                }
                
                sql.withBatch(SQL_EMPLOYES_GROUPES) {
                    psGroups ->
                    p.groups.each() {
                        Group group ->
                        psGroups.addBatch(idEmployee : p.id, idGroupe : group.id)
                    }
                }
                
            }
        }
    }
}

