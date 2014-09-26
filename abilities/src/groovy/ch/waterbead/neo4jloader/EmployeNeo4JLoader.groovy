package ch.waterbead.neo4jloader

import ch.waterbead.config.Config
import ch.waterbead.domain.Ability
import ch.waterbead.domain.Employe
import ch.waterbead.domain.Group
import ch.waterbead.domain.Project
import org.neo4j.graphdb.DynamicLabel
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.Transaction
import org.neo4j.graphdb.RelationshipType
import org.neo4j.graphdb.Node
import org.neo4j.graphdb.Relationship
import org.neo4j.graphdb.schema.Schema

/**
 * Class that load a list of employes into the graph database.
*/
class EmployeNeo4JLoader extends Neo4JLoader {
    private Map<Long, Long> groupsNodes;
    private Map<Long, Long> projectsNodes;
    private Map<Long, Long> abilitiesNodes;
    
    EmployeNeo4JLoader(Map<Long, Long> groupsNodes, Map<Long, Long> projectsNodes, Map<Long, Long> abilitiesNodes) {
        this.groupsNodes = groupsNodes;
        this.projectsNodes = projectsNodes;
        this.abilitiesNodes = abilitiesNodes;
    }
    
    def load(List<Employe> employes) {
        employes.each() {
            Employe e ->
            
            if(e.id % 10000 == 0) {
                println "${e.id}/${employes.size()}"
            }
            Map properties = [:]
            properties.put(Neo4JRegistry.PROPERTY_EMPLOYE_ID, e.id)
            properties.put(Neo4JRegistry.PROPERTY_EMPLOYE_LASTNAME, e.lastname)
            properties.put(Neo4JRegistry.PROPERTY_EMPLOYE_FIRSTNAME, e.firstname)
            properties.put(Neo4JRegistry.PROPERTY_EMPLOYE_YEAROFBIRTHDAY, e.yearOfBirthDay)
            long nodeEmployeId = batchInserter.createNode(properties, Neo4JRegistry.LABEL_EMPLOYE)
          
            e.groups.each() {
                Group g ->
                    long nodeGroupId = groupsNodes.get(g.id)
                    println "${nodeEmployeId} ${g.id}"
                    batchInserter.createRelationship(nodeEmployeId, nodeGroupId, Neo4JRegistry$RelationEmployes.TAKE_PART, null)
            }
           
            e.projects.each() {
                Project p -> 
                    long nodeProjectId = projectsNodes.get(p.id)
                    batchInserter.createRelationship(nodeEmployeId, nodeProjectId, Neo4JRegistry$RelationEmployes.WORK, null)
            }
            
            e.abilities.each() {
                Ability a ->
                    long nodeAbilityId = abilitiesNodes.get(a.id)
                    batchInserter.createRelationship(nodeEmployeId, nodeAbilityId, Neo4JRegistry$RelationEmployes.MASTER, null)
            }
        }
    }
}

