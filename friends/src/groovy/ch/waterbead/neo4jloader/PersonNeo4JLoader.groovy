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
    
    def load(LinkedList<Person> people) {       
        addConstraints()
        populatePeople(people);
        createRelationships(people);
    }
    
    private def addConstraints() {
        beginTransaction()
        graphDb.schema().indexFor(Neo4JRegistry.LABEL_PERSONNE).on(Neo4JRegistry.PROPERTY_PERSONNE_ID).create()
        commitTransaction()
                
    }
    
    private def populatePeople(def people) {
        println "Prepared sublists"
        def subLists = people.collate(100000)
        println "Sublists prepared"
        subLists.each() {
            list -> 
            loadPeople(list)
        }
    }
    
    def loadPeople(def people) {
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
    
    def createRelationships(def people) {
        beginTransaction()
        while(!people.empty) {
            Person p = people.removeFirst();
            if(p.id % 10000 == 0) {
                commitTransaction();
                beginTransaction();
            }
            def nodePerson = map.get(p.id)
            if(p.id%100==0)
                println "Create relationship for person ${p.id}"
            p.friends.each() {
                Long friendId ->
                def nodeFriend = map.get(friendId)
                nodePerson.createRelationshipTo(nodeFriend, Neo4JRegistry$RelationPersonne.FRIEND_OF)
            }
        }
        commitTransaction()
    }
}

