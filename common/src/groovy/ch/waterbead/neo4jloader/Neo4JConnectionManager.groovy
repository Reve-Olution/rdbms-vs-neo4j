package ch.waterbead.neo4jloader

import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.Relationship
import org.neo4j.graphdb.Transaction
import org.neo4j.graphdb.factory.GraphDatabaseFactory
import org.neo4j.graphdb.DynamicLabel
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserters;

class Neo4JConnectionManager {
    static BatchInserter batchInserter;
    
    static BatchInserter getInserter() {
        return batchInserter;
    }
    
    static def finish() {
        batchInserter.shutdown();
    }
    
    static initGraphDatabase(def neo4JPath) {
        Map<String, String> config = new HashMap<>();
        config.put( "neostore.nodestore.db.mapped_memory", "90M" );
        config.put( "neostore.relationshipstore.db.mapped_memory", "3G" );
        config.put( "neostore.propertystore.db.mapped_memory", "50M" );
        config.put( "neostore.propertystore.db.strings.mapped_memory", "100M" );
        config.put( "neostore.propertystore.db.arrays.mapped_memory", "0M");
        config.put( "cache_type", "none")
        if(batchInserter!=null) {
          throw new IllegalAccessError("initGraphDatabase already called")
        }
        batchInserter = BatchInserters.inserter(neo4JPath, config)
    }
}

