package ch.waterbead.sqlloader

import ch.waterbead.domain.Person
import ch.waterbead.generator.RandomGenerator

class PersonSQLLoader extends SQLLoader {
        private static final SQL_PERSONNES = "INSERT INTO PERSONNES (id,nom) VALUES (:id,:nom)"
        private static final SQL_FRIENDS = "INSERT INTO AMIS (id_personnes1, id_personnes2) VALUES (:id_personnes1, :id_personnes2)"
        
	def load(List<Person> personnes, int nbFriendsToGenerate) {
            sql.withBatch(SQL_PERSONNES) {
                psPerson ->
                personnes.each() {
                    Person person ->
                    if(person.id % 10000 == 0) {    
                        println "People generation : ${person.id}/${personnes.size()}"
                    }
                    psPerson.addBatch(id : person.id, nom: person.name) 
                    
                    sql.withBatch(SQL_FRIENDS) {
                        psFriend ->
                        nbFriendsToGenerate.times() {
                            long friendId = RandomGenerator.getForIndexOf0(personnes.size())
                            psFriend.addBatch(id_personnes1 : person.id, id_personnes2 : friendId)
                        }
                    }
                }
            }
        }
}

