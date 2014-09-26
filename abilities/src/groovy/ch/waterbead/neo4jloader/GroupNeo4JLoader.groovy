package ch.waterbead.neo4jloader

import ch.waterbead.domain.Ability
import ch.waterbead.domain.Employe
import ch.waterbead.domain.Group
import org.neo4j.graphdb.DynamicLabel
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.Transaction
import org.neo4j.graphdb.RelationshipType
import org.neo4j.graphdb.Node
import org.neo4j.graphdb.schema.Schema

/**
 * Class that load groups into the graph database
*/
class GroupNeo4JLoader extends Neo4JLoader{
    /**
     * Load a list of groups into the graph database and return a map that contains the group id 
     * as a key and the created node id as a value.
    */
   Map<Long, Long> load(List<Group> groups) {
       Map<Long, Long> groupsNodes = [:]
        groups.each() {
            Group g->
            def properties = [:]
            properties.put(Neo4JRegistry.PROPERTY_GROUP_ID, g.id)
            properties.put(Neo4JRegistry.PROPERTY_GROUP_NAME, g.name)
            long groupNodeId = batchInserter.createNode(properties,Neo4JRegistry.LABEL_GROUP)
            groupsNodes.put(g.id, groupNodeId)
        }
        return groupsNodes;
    }
}

