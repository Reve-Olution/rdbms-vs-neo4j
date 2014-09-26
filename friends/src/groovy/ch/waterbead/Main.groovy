package ch.waterbead

import ch.waterbead.generator.PersonGenerator
import ch.waterbead.neo4jloader.ConstraintsNeo4JLoader
import ch.waterbead.sqlloader.PersonSQLLoader
import ch.waterbead.config.ConfigLoader
import ch.waterbead.config.Config
import ch.waterbead.neo4jloader.PersonNeo4JLoader
import ch.waterbead.neo4jloader.Neo4JConnectionManager

def config = ConfigLoader.load()

File file = new File(config.neo4j.path);
file.deleteDir();


def personGenerator = new PersonGenerator(Config.FILE_FIRST_NAMES)

def personnes = personGenerator.generate(config.population)


if(config.neo4j.path) {
    Neo4JConnectionManager.initGraphDatabase(config.neo4j.path)
    def personNeo4JLoader = new PersonNeo4JLoader();
    def constraintNeo4JLoader = new ConstraintsNeo4JLoader();
    personNeo4JLoader.load(personnes, config.numberOfFriends)
    constraintNeo4JLoader.addConstraints();
    Neo4JConnectionManager.finish();
}

if(config.rdbms.url) {
}




