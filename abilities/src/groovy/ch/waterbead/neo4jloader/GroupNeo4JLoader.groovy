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

class GroupNeo4JLoader extends Neo4JLoader{
    def load(List<Group> groups) {
        beginTransaction()
        groups.each() {
            Group g->
            Node node = graphDb.createNode(Neo4JRegistry.LABEL_GROUP)
            node.setProperty(Neo4JRegistry.PROPERTY_GROUP_ID, g.id);
            node.setProperty(Neo4JRegistry.PROPERTY_GROUP_NAME, g.name);
        }
        commitTransaction()
    }
}

