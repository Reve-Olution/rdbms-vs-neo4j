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
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.factory.GraphDatabaseFactory


GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase("/home/geiser/miam2")
db.beginTx()
FriendsFinder tr = new FriendsFinder(db)
println tr.countFriends(1)
db.shutdown()




