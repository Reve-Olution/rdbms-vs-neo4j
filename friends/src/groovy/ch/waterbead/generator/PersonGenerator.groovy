package ch.waterbead.generator

import ch.waterbead.config.Config
import ch.waterbead.domain.Person
import ch.waterbead.loader.FileLoader;

class PersonGenerator {
    def generate(int number) {
        def people = [];
        List firstnames = FileLoader.load(Config.FILE_FIRST_NAMES)
        def nbPeople = firstnames.size()

        number.times() {
            id ->
            def firstId = id + 1;
            int randomName = RandomGenerator.get(nbPeople)
            Person person = new Person(id : firstId, name : firstnames[randomName])
            people.add(person)
        }
        println "People generated"
        return people;
    }
}