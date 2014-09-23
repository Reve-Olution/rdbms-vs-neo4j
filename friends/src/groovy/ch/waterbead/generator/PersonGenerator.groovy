package ch.waterbead.generator

import ch.waterbead.config.Config
import ch.waterbead.domain.Person
import ch.waterbead.loader.FileLoader;

class PersonGenerator {
    def generate() {
        LinkedList people = [] as LinkedList;
        List firstnames = FileLoader.load(Config.FILE_FIRST_NAMES)
        def nbPeople = firstnames.size()

        Config.population.times() {
            id ->
            int newId = id + 1;
            int randomName = RandomGenerator.get(nbPeople)
            Person person = new Person(id : newId, name : firstnames[randomName])
            people.add(person)
        }
        
        println "People created"
        addFriends(people)
        println "Friends created"
        return people;
    }
    
    def addFriends(def people) {
        int nbPeople = people.size()
        people.each() {
            Person p ->
            if(p.id % 1000 == 0)
            println "Add friends to ${p.id}/${nbPeople}"
            Config.NB_FRIENDS.times {
                addCustomFriend(p, nbPeople)
            }
        }
    }
    
    def addCustomFriend(Person person, int nbPeople) {
        int randomIdPeople = RandomGenerator.getForIndexOf0(nbPeople)
        person.addFriend(randomIdPeople)
    }
}