package ch.waterbead.neo4jloader

import org.neo4j.graphdb.DynamicLabel
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.RelationshipType

class Neo4JRegistry {
	   
    public static final PERSONNE = "Personne"
    
    public static final Label LABEL_PERSONNE= new DynamicLabel(PERSONNE);
    
    public static final String PROPERTY_PERSONNE_ID = "id"
    public static final String PROPERTY_PERSONNE_LASTNAME = "nom"
    
    public static enum RelationPersonne implements RelationshipType {
        FRIEND_OF
    }
}

