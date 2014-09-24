package ch.waterbead.generator

import ch.waterbead.domain.Person
import ch.waterbead.loader.FileLoader;

class PersonGenerator {
    private List<String> firstnames;
    
    PersonGenerator(String fileFirstNames) {
        firstnames = FileLoader.load(fileFirstNames);
    }
    
    def generate(int numberToGenerate) {
        println "People generation"
        
        def people = [];
        def nbPeople = firstnames.size()

        numberToGenerate.times() {
            id ->
            if(id % 100000 == 0) {
                println "People created : ${id}/${numberToGenerate}"
            }
            def newId = id + 1;
            int randomName = RandomGenerator.get(nbPeople)
            Person person = new Person(id : newId, name : firstnames[randomName])
            people.add(person)
        }
        println "People generated"
        return people;
    }
}