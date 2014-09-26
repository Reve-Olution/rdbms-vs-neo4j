package ch.waterbead.neo4jloader

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
import org.neo4j.graphdb.schema.Schema

/**
 * Class that load projects into the graphdatabase
 **/
class ProjectNeo4JLoader extends Neo4JLoader{
    /**
     * Load a list of projects into the graph database and return a map 
     * that contains the id of the project as a key and the id of the node as a value.
    */
    Map<Long, Long> load(List<Project> projects) {
        Map<Long, Long>  projectsNodes = [:]
        projects.each() {
            Project p->
            Map<String, Object> properties = [:]
            properties.put(Neo4JRegistry.PROPERTY_GROUP_ID, p.id)
            properties.put(Neo4JRegistry.PROPERTY_GROUP_NAME, p.name)
            def projectNodeId = batchInserter.createNode(properties,Neo4JRegistry.LABEL_PROJECT)
            projectsNodes.put(p.id, projectNodeId)
        }
        return projectsNodes;
    }
}

