package ch.waterbead.neo4jloader

import ch.waterbead.config.Config

class GlobalNeo4Loader {
    def static load(def abilities, def projects, def groups, def employes) {
        File file = new File(Config.neo4JPath);
        file.deleteDir();
        
        AbilityNeo4JLoader aloader = new AbilityNeo4JLoader()
        ProjectNeo4JLoader ploader = new ProjectNeo4JLoader()
        GroupNeo4JLoader gloader = new GroupNeo4JLoader()
        EmployeNeo4JLoader eloader = new EmployeNeo4JLoader()
        
        Neo4JConnectionManager.beginTransaction()
        
        aloader.load(abilities)
        ploader.load(projects)
        gloader.load(groups)
        eloader.load(employes)
        
        Neo4JConnectionManager.commitTransaction()
    }
}

