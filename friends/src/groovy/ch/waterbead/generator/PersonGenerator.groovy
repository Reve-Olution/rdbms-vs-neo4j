package ch.waterbead.generator

import ch.waterbead.config.Config
import ch.waterbead.domain.Person
import ch.waterbead.loader.FileLoader;

class PersonGenerator {
    def generate() {
        def people = []
        List firstnames = FileLoader.load(Config.FILE_FIRST_NAMES)

        Config.population.times() {
            id ->
            int newId = id + 1;
            int randomName = RandomGenerator.get(firstnames.size())
            Person person = new Person(id : newId, name : firstnames[randomName])
            people.add(person)
        }
        
        return firstnames;
    }
}

