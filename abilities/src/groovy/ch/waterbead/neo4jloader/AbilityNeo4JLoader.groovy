package ch.waterbead.neo4jloader

import ch.waterbead.domain.Ability
import ch.waterbead.domain.Employe
import org.neo4j.graphdb.DynamicLabel
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.Transaction
import org.neo4j.graphdb.RelationshipType
import org.neo4j.graphdb.Node
import org.neo4j.graphdb.schema.Schema

/**
 * Class that load the abilities into the graph database
*/
class AbilityNeo4JLoader extends Neo4JLoader{
    /**
     * Load a list of abilities into the graph database and return a map which 
     * contains the ability id as a key and the node created id as value.
    */
    def load(List<Ability> abilities) {
        Map<Long, Long> abilitiesNodes = [:]
        abilities.each() {
            Ability a->
            Map properties = [:]    
            properties.put(Neo4JRegistry.PROPERTY_ABILITY_ID, a.id)
            properties.put(Neo4JRegistry.PROPERTY_ABILITY_NAME, a.name)
            long abilityNodeId = batchInserter.createNode(properties,Neo4JRegistry.LABEL_ABILITY)
            abilitiesNodes.put(a.id, abilityNodeId)
            
        }
        return abilitiesNodes;
    }
}

