package ch.waterbead

import ch.waterbead.generator.PersonGenerator
import ch.waterbead.neo4jloader.ConstraintsNeo4JLoader
import ch.waterbead.sqlloader.PersonSQLLoader
import ch.waterbead.config.ConfigLoader
import ch.waterbead.config.Config
import ch.waterbead.neo4jloader.PersonNeo4JLoader
import ch.waterbead.neo4jloader.Neo4JConnectionManager
import ch.waterbead.sqlloader.Cleaner
import ch.waterbead.sqlloader.ConnectionManager

def config = ConfigLoader.load()

def personGenerator = new PersonGenerator(Config.FILE_FIRST_NAMES)

def personnes = personGenerator.generate(config.population)


if(config.neo4j.path) {
    /**Delete the old database */
    File file = new File(config.neo4j.path);
    file.deleteDir();
    
    /** Init the graph database */
    Neo4JConnectionManager.initGraphDatabase(config.neo4j.path)
    
    /** Import people and constraints */
    def personNeo4JLoader = new PersonNeo4JLoader();
    def constraintNeo4JLoader = new ConstraintsNeo4JLoader();
    personNeo4JLoader.load(personnes, config.numberOfFriends)
    constraintNeo4JLoader.addConstraints();
    
    /** Close database */
    Neo4JConnectionManager.finish();
}

if(config.rdbms.url) {
    ConnectionManager.initSQL(config)
    Cleaner.clean();
    def personSQLLoader = new PersonSQLLoader();
    personSQLLoader.load(personnes, config.numberOfFriends)
    Cleaner.addConstraints();
}




