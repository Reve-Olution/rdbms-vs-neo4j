package ch.waterbead.generator

import ch.waterbead.config.Config
import ch.waterbead.domain.Person
import ch.waterbead.loader.FileLoader;

class PersonGenerator {
    def generate() {
        def people = []
        List firstnames = FileLoader.load(Config.FILE_FIRST_NAMES)
        def nbPeople = firstnames.size()

        Config.population.times() {
            id ->
            int newId = id + 1;
            int randomName = RandomGenerator.get(nbPeople)
            Person person = new Person(id : newId, name : firstnames[randomName])
            
            Config.NB_FRIENDS.times {
                addCustomFriend(person, nbPeople)
            }
                
            people.add(person)
        }
        
        return firstnames;
    }
    
    def addCustomFriend(Person person, int nbPeople) {
        int randomIdPeople = RandomGenerator.get(nbPeople)
        Person friend = new Person(id : randomIdPeople)
        person.addFriend(friend)
    }
}

