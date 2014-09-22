package ch.waterbead.neo4jloader

import ch.waterbead.domain.Person
import org.neo4j.graphdb.DynamicLabel
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.Transaction
import org.neo4j.graphdb.RelationshipType
import org.neo4j.graphdb.Node
import org.neo4j.graphdb.schema.Schema

class PersonNeo4JLoader extends Neo4JLoader{
    Map<Long, Node>  map = [:]
    
    def load(List<Person> people) {       
        beginTransaction()
        graphDb.schema().indexFor(Neo4JRegistry.LABEL_PERSONNE).on(Neo4JRegistry.PROPERTY_PERSONNE_ID).create()
        commitTransaction()
        
        def subLists = people.collate(10000)
        println "Sublists prepared"
        subLists.each() {
            list -> 
            loadPart(list)
        }
        
        beginTransaction()
        people.each() {
            Person p ->
            if(p.id % 10000 == 0) {
                commitTransaction();
                beginTransaction();
            }
            //def nodePerson = findNodePersonById(p.id)
            def nodePerson = map.get(p.id)
            if(p.id%100==0)
                println "Create relationship for person ${p.id}"
            p.friends.each() {
                Person friend ->
                //def nodeFriend = findNodePersonById(friend.id)
                def nodeFriend = map.get(friend.id)
                nodePerson.createRelationshipTo(nodeFriend, Neo4JRegistry$RelationPersonne.FRIEND_OF)
            }
        }
        commitTransaction()
    }
        
    Node findNodePersonById(long id) {
        Node node = null;
        graphDb.findNodesByLabelAndProperty(Neo4JRegistry.LABEL_PERSONNE, Neo4JRegistry.PROPERTY_PERSONNE_ID, id).each {
            Node nodeFound ->
            node = nodeFound
        }
        return node;
    }   
    
    def loadPart(List<Person> people) {
        println "Loading people"
        beginTransaction()
        people.each() {
            Person p->
            Node node = graphDb.createNode(Neo4JRegistry.LABEL_PERSONNE)
            node.setProperty(Neo4JRegistry.PROPERTY_PERSONNE_ID, p.id);
            node.setProperty(Neo4JRegistry.PROPERTY_PERSONNE_LASTNAME, p.name);
            
            map.put(p.id, node);
        }
        
        println "Commit transaction"
        commitTransaction()        
        println "Transaction commited"
    } 
}

