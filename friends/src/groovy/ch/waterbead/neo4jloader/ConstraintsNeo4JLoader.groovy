package ch.waterbead.neo4jloader

class ConstraintsNeo4JLoader extends Neo4JLoader {
    def addConstraints() {
        beginTransaction()
        graphDb.schema().indexFor(Neo4JRegistry.LABEL_PERSONNE).on(Neo4JRegistry.PROPERTY_PERSONNE_ID).create()
        commitTransaction()
    }
}

