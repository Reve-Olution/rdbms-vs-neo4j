package ch.waterbead.neo4jloader

import org.neo4j.graphdb.GraphDatabaseService

abstract class Neo4JLoader {
	protected final GraphDatabaseService graphDb = Neo4JConnectionManager.getGraph()
        
        def commitTransaction() {
            Neo4JConnectionManager.commitTransaction()
        }
        
        def beginTransaction() {
            Neo4JConnectionManager.beginTransaction()
        }
}

