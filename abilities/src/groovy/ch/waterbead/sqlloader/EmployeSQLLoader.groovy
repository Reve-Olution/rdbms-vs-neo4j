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
    private static final SQL_EMPLOYES_COMPETENCES = "INSERT INTO EMPLOYES_COMPETENCES (id, idEmploye, idCompetence, note) VALUES (:id, :idEmploye, :idCompetence, :note)"
    private static final SQL_EMPLOYES_GROUPES = "INSERT INTO EMPLOYES_GROUPES (idEmploye, idGroupe) VALUES (:idEmploye, :idGroupe)"
    private static final SQL_EMPLOYES_PROJETS = "INSERT INTO EMPLOYES_PROJETS (idEmploye, idProjet) VALUES (:idEmploye, idProjet)"
    def load(List<Employe> people) {
        sql.withBatch(SQL_EMPLOYES) {
            ps ->
            people.each() {
                Employe p ->
                
                if(p.id % 10000 == 0) {
                    println "${p.id}/${people.size()}"
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
                        psProjets.addBatch(idEmploye : p.id, idProjet : project.id)
                    }
                }
                
                sql.withBatch(SQL_EMPLOYES_GROUPES) {
                    psGroups ->
                    p.groups.each() {
                        Group group ->
                        psGroups.addBatch(idEmploye : p.id, idGroupe : group.id)
                    }
                }
            }
        }
    }
}

