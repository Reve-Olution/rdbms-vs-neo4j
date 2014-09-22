package ch.waterbead

import ch.waterbead.generator.PersonGenerator
import ch.waterbead.sqlloader.PersonSQLLoader
import ch.waterbead.config.ConfigLoader
import ch.waterbead.neo4jloader.PersonNeo4JLoader

File file = new File("/home/geiser/test.db");
file.deleteDir();

ConfigLoader.load()

def personGenerator = new PersonGenerator()

def personnes = personGenerator.generate()

if(ConfigLoader.mustRDBMSPopulated) {
    def personSQLLoader = new PersonSQLLoader();
    personSQLLoader.load(personnes);
}
if(ConfigLoader.mustNeo4JPopulated) {
    def personNeo4JLoader = new PersonNeo4JLoader();
    personNeo4JLoader.load(personnes)
}
