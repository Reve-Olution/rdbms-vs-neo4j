package ch.waterbead

import ch.waterbead.generator.PersonGenerator
import ch.waterbead.neo4jloader.ConstraintsNeo4JLoader
import ch.waterbead.sqlloader.PersonSQLLoader
import ch.waterbead.config.ConfigLoader
import ch.waterbead.config.Config
import ch.waterbead.neo4jloader.PersonNeo4JLoader
import ch.waterbead.neo4jloader.Neo4JConnectionManager

File file = new File("/home/geiser/test.db");
file.deleteDir();

ConfigLoader.load()

def personGenerator = new PersonGenerator()
def personNeo4JLoader = new PersonNeo4JLoader();
ConstraintsNeo4JLoader constraintNeo4JLoader = new ConstraintsNeo4JLoader();

def personnes = personGenerator.generate(Config.population)
personNeo4JLoader.load(personnes)

//constraintNeo4JLoader.addConstraints();


Neo4JConnectionManager.finish();

