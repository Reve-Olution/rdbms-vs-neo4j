package ch.waterbead.neo4jloader

import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.Relationship
import org.neo4j.graphdb.Transaction
import org.neo4j.graphdb.factory.GraphDatabaseFactory
import ch.waterbead.config.Config
import org.neo4j.graphdb.DynamicLabel

class Neo4JConnectionManager {
    static GraphDatabaseService graphDb
    static Transaction transaction
    static boolean openTransaction = false
    
    static {
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(Config.neo4JPath)
    }
    
    static GraphDatabaseService getGraph() {
        return graphDb;
    }
    
    static def beginTransaction() {
        if(openTransaction) {
            throw new IllegalAccessException("A transaction is already open !")
        }
        transaction = graphDb.beginTx();
        openTransaction = true;
    }
    
    static def commitTransaction() {
        transaction.success();
        transaction.finish();
        openTransaction = false;
    }
}

