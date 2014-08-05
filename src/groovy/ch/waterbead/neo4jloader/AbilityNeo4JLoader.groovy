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

class AbilityNeo4JLoader extends Neo4JLoader{
    def load(List<Ability> abilities) {
        beginTransaction()
        abilities.each() {
            Ability a->
            Node node = graphDb.createNode(Neo4JRegistry.LABEL_ABILITY)
            node.setProperty(Neo4JRegistry.PROPERTY_ABILITY_ID, a.id);
            node.setProperty(Neo4JRegistry.PROPERTY_ABILITY_NAME, a.name);
        }
        commitTransaction()
    }
}

