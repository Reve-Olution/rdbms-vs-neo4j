package ch.waterbead.neo4jloader

import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserters;

abstract class Neo4JLoader {
    protected final BatchInserter batchInserter = Neo4JConnectionManager.getInserter();
}

