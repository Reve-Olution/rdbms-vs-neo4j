package ch.waterbead.neo4jloader

import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.Relationship
import org.neo4j.graphdb.Transaction
import org.neo4j.graphdb.factory.GraphDatabaseFactory
import org.neo4j.graphdb.DynamicLabel
import org.neo4j.unsafe.batchinsert.BatchInserters;

class Neo4JConnectionManager {
    static GraphDatabaseService graphDb
    static Transaction transaction
    static boolean openTransaction = false
    
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
    
    static def finish() {
        graphDb.shutdown();
    }
    
    static initGraphDatabase(def neo4JPath) {
        if(graphDb!=null)
          throw new IllegalAccessError("initGraphDatabase already called")
        graphDb = BatchInserters.batchDatabase(neo4JPath);
        BatchInserters.ba
            //graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(neo4JPath)
    }
}

