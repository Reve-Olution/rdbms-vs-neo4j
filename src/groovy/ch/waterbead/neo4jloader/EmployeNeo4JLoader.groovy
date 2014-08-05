package ch.waterbead.neo4jloader

import ch.waterbead.config.Config
import ch.waterbead.domain.Employe
import org.neo4j.graphdb.DynamicLabel
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.Transaction
import org.neo4j.graphdb.RelationshipType
import org.neo4j.graphdb.Node
import org.neo4j.graphdb.Relationship
import org.neo4j.graphdb.schema.Schema

class EmployeNeo4JLoader extends Neo4JLoader {
    
    def load(List<Employe> employes) {
//        graphDb.schema().constraintFor(LABEL_EMPLOYE).assertPropertyIsUnique(PROPERTY_EMPLOYE_ID).create()
//        graphDb.schema().constraintFor(LABEL_GROUP).assertPropertyIsUnique(PROPERTY_GROUP_ID)
//        graphDb.schema().constraintFor(LABEL_PROJECT).assertPropertyIsUnique(PROPERTY_PROJECT_ID)
//        graphDb.schema().constraintFor(LABEL_ABILITY).assertPropertyIsUnique(PROPERTY_ABILITY_ID)
        
        beginTransaction()
        employes.each() {
            Employe e ->
            
            if(Config.DEBUG) {
                println "${e.id} : ${e.firstname}"
            }
            Node node = graphDb.createNode(Neo4JRegistry.LABEL_EMPLOYE)
            node.setProperty(Neo4JRegistry.PROPERTY_EMPLOYE_ID, e.id);
            node.setProperty(Neo4JRegistry.PROPERTY_EMPLOYE_LASTNAME, e.lastname);
            node.setProperty(Neo4JRegistry.PROPERTY_EMPLOYE_FIRSTNAME, e.firstname);
            node.setProperty(Neo4JRegistry.PROPERTY_EMPLOYE_YEAROFBIRTHDAY, e.yearOfBirthDay);
          
            e.groups.each() {
                g ->
                graphDb.findNodesByLabelAndProperty(Neo4JRegistry.LABEL_GROUP, Neo4JRegistry.PROPERTY_GROUP_ID, g.id).each {
                    nodeGroup ->
                    node.createRelationshipTo(nodeGroup, Neo4JRegistry$RelationEmployes.TAKE_PART)
                }
            }
            
            e.projects.each() {
                p -> 
                graphDb.findNodesByLabelAndProperty(Neo4JRegistry.LABEL_PROJECT, Neo4JRegistry.PROPERTY_GROUP_ID, p.id).each {
                    nodeProject ->
                    node.createRelationshipTo(nodeProject, Neo4JRegistry$RelationEmployes.WORK)
                }
            }
            
            e.abilities.each() {
                a ->
                graphDb.findNodesByLabelAndProperty(Neo4JRegistry.LABEL_ABILITY, Neo4JRegistry.PROPERTY_ABILITY_ID, a.id).each {
                    nodeAbility ->
                    Relationship relAbility = node.createRelationshipTo(nodeAbility,Neo4JRegistry$RelationEmployes.MASTER)
                    relAbility.setProperty(Neo4JRegistry.PROPERTY_ABILITY_SCORE, a.score)
                }
            }
        }
        commitTransaction()
    }
}

