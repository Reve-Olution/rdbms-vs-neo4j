package ch.waterbead.neo4jloader

import ch.waterbead.domain.Person
import ch.waterbead.generator.RandomGenerator
import ch.waterbead.config.Config
import org.neo4j.graphdb.DynamicLabel
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.Transaction
import org.neo4j.graphdb.RelationshipType
import org.neo4j.graphdb.Node
import org.neo4j.graphdb.schema.Schema

class PersonNeo4JLoader extends Neo4JLoader{
    Map<Long, Node>  map = [:]
    
    def load(def people) {       
        loadPeople(people);
        loadFriends(people);
    }
    
    def loadPeople(def people) {
        println "Loading people"
        people.each() {
            Person p->
            Node node = graphDb.createNode(Neo4JRegistry.LABEL_PERSONNE)
            node.setProperty(Neo4JRegistry.PROPERTY_PERSONNE_ID, p.id);
            node.setProperty(Neo4JRegistry.PROPERTY_PERSONNE_LASTNAME, p.name);
            map.put(p.id, node);
        }
        println "People loaded"
    } 
    
    def loadFriends(def people)  {
        def peopleToGenerate = people.size()
        people.each() {
            Person p ->
            if(p.id % 1000 == 0) {
                println "commit friends ${p.id}/${peopleToGenerate}"
            }
            Node nodePerson = map.get(p.id)
            Config.NB_FRIENDS.times() {
                //Pick a random people among the list
                long friendId = RandomGenerator.getForIndexOf0(peopleToGenerate)
                Node nodeFriend = map.get(friendId)
                createRelationship(nodePerson, nodeFriend)
            }
        }
    }
    
    private createRelationship(Node nodePerson, Node nodeFriend) {
        nodePerson.createRelationshipTo(nodeFriend, Neo4JRegistry$RelationPersonne.FRIEND_OF)
    }
}

