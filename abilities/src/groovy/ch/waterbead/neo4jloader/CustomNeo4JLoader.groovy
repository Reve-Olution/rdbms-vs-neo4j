package ch.waterbead.neo4jloader

/**
 * Load the constraints into the graph database
*/
class CustomNeo4JLoader extends Neo4JLoader {
	def addConstraints() {
            /**
             * Create indexes
            */
            batchInserter.createDeferredSchemaIndex(Neo4JRegistry.LABEL_GROUP).on(Neo4JRegistry.PROPERTY_GROUP_NAME).create();
            batchInserter.createDeferredSchemaIndex(Neo4JRegistry.LABEL_ABILITY).on(Neo4JRegistry.PROPERTY_ABILITY_NAME).create();
            
            /**
             * Create constraints
            */
           batchInserter.createDeferredConstraint(Neo4JRegistry.LABEL_GROUP)assertPropertyIsUnique(Neo4JRegistry.PROPERTY_GROUP_ID).create()
           batchInserter.createDeferredConstraint(Neo4JRegistry.LABEL_PROJECT).assertPropertyIsUnique(Neo4JRegistry.PROPERTY_PROJECT_ID).create()
           batchInserter.createDeferredConstraint(Neo4JRegistry.LABEL_ABILITY).assertPropertyIsUnique(Neo4JRegistry.PROPERTY_ABILITY_ID).create()
           batchInserter.createDeferredConstraint(Neo4JRegistry.LABEL_EMPLOYE).assertPropertyIsUnique(Neo4JRegistry.PROPERTY_EMPLOYE_ID).create()
        }
}

