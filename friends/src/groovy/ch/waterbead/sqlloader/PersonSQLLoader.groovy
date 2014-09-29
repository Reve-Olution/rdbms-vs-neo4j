package ch.waterbead.sqlloader

import ch.waterbead.domain.Person
import ch.waterbead.generator.RandomGenerator
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class PersonSQLLoader extends SQLLoader {
        private static final SQL_PERSONNES = "INSERT INTO PERSONNES (id,nom) VALUES (:id,:nom)"
        private static final SQL_FRIENDS = "INSERT INTO AMIS (id_personnes1, id_personnes2) VALUES (:id_personnes1, :id_personnes2)"
        
        private AtomicInteger counter = new AtomicInteger()
        private int numberOfPeople;
        
        private static final int COLLATE_PARAMETER = 500;
        
	def load(List<Person> people, int nbOfFriends) {
            def listPeople = people.collate(COLLATE_PARAMETER)
            numberOfPeople = people.size()
        
            Executor pool = Executors.newFixedThreadPool(8);
            def defer = { c -> pool.submit(c as Callable) }
               
            listPeople.each() {
                list ->
                defer{this.&loadPeople(list, nbOfFriends)};
            }
            
            pool.shutdown()
            
            try {
                pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                println e.message
            }
    }
        
    def loadPeople(def personnes, int nbFriendsToGenerate) {
        sql.withTransaction() {
        sql.withBatch(SQL_PERSONNES) {
            psPerson ->
            personnes.each() {
                Person person ->
                counter.incrementAndGet()
                psPerson.addBatch(id : person.id, nom: person.name) 
                println "${counter.get()}/${numberOfPeople}"

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
}

