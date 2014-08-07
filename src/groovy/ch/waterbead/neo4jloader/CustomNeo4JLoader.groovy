package ch.waterbead.neo4jloader

class CustomNeo4JLoader extends Neo4JLoader {
	def addConstraints() {
            beginTransaction()
            graphDb.schema().constraintFor(Neo4JRegistry.LABEL_GROUP).assertPropertyIsUnique(Neo4JRegistry.PROPERTY_GROUP_ID)
            graphDb.schema().constraintFor(Neo4JRegistry.LABEL_PROJECT).assertPropertyIsUnique(Neo4JRegistry.PROPERTY_PROJECT_ID)
            graphDb.schema().constraintFor(Neo4JRegistry.LABEL_ABILITY).assertPropertyIsUnique(Neo4JRegistry.PROPERTY_ABILITY_ID)
            graphDb.schema().indexFor(Neo4JRegistry.LABEL_EMPLOYE).on(Neo4JRegistry.PROPERTY_GROUP_NAME)
            graphDb.schema().constraintFor(Neo4JRegistry.LABEL_EMPLOYE).assertPropertyIsUnique(Neo4JRegistry.PROPERTY_EMPLOYE_ID).create()
            commitTransaction()
        }
}

