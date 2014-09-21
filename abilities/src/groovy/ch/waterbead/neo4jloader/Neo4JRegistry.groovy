package ch.waterbead.neo4jloader

import org.neo4j.graphdb.DynamicLabel
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.RelationshipType

class Neo4JRegistry {
	   
    public static final EMPLOYE = "Employe"
    public static final ABILITY = "Competence"
    public static final GROUP = "Group"
    public static final PROJECT = "Project"
    
    public static final Label LABEL_EMPLOYE= new DynamicLabel(EMPLOYE);
    public static final Label LABEL_ABILITY = new DynamicLabel(ABILITY);
    public static final Label LABEL_GROUP = new DynamicLabel(GROUP);
    public static final Label LABEL_PROJECT = new DynamicLabel(PROJECT);
    
    public static final String PROPERTY_EMPLOYE_ID = "id"
    public static final String PROPERTY_EMPLOYE_LASTNAME = "nom"
    public static final String PROPERTY_EMPLOYE_FIRSTNAME = "prenom"
    public static final String PROPERTY_EMPLOYE_YEAROFBIRTHDAY = "anneenaissance"
    
    public static final String PROPERTY_ABILITY_ID = "id"
    public static final String PROPERTY_ABILITY_NAME = "nom"
    public static final String PROPERTY_ABILITY_SCORE = "note"
    
    public static final String PROPERTY_GROUP_ID = "id"
    public static final String PROPERTY_GROUP_NAME = "nom"
    
    public static final String PROPERTY_PROJECT_ID = "id"
    public static final String PROPERTY_PROJECT_NAME = "nom"
    
    public static enum RelationEmployes implements RelationshipType {
        WORK,
        MASTER,
        TAKE_PART
    }
}

