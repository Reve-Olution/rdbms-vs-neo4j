package ch.waterbead.sqlloader

import ch.waterbead.domain.Person

class PersonSQLLoader extends SQLLoader {
        private static final SQL_PERSONNES = "INSERT INTO PERSONNES (id,nom) VALUES (:id,:nom)"
        private static final SQL_FRIENDS = "INSERT INTO AMIS (id_personnes1, id_personnes2) VALUES (:id_personnes1, :id_personnes2)"
        
	def load(List<Person> personnes) {
            sql.withBatch(SQL_PERSONNES) {
                ps ->
                personnes.each() {
                    Person person ->
                    ps.addBatch(id : person.id, nom: person.name) 
                }
            }
            
            sql.withBatch(SQL_FRIENDS) {
                ps ->
                personnes.each() {
                    Person person ->
                    person.friends.each() {
                        Person friend ->
                        ps.addBatch(id_personnes1 : person.id, id_personnes2 : friend.id)
                    }
                }
            }
        }
}

