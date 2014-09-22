package ch.waterbead.neo4jloader

import ch.waterbead.config.Config

class GlobalNeo4Loader {
    def static load(def abilities, def projects, def groups, def employes) {
        File file = new File("/home/geiser/test.db");
        file.deleteDir();
        
        AbilityNeo4JLoader aloader = new AbilityNeo4JLoader()
        ProjectNeo4JLoader ploader = new ProjectNeo4JLoader()
        GroupNeo4JLoader gloader = new GroupNeo4JLoader()
        EmployeNeo4JLoader eloader = new EmployeNeo4JLoader()
        CustomNeo4JLoader cloader = new CustomNeo4JLoader();
        
        def subLists = employes.collate(1000)
        
        aloader.load(abilities)
        ploader.load(projects)
        gloader.load(groups)
        
        subLists.each() {
            list ->
            eloader.load(list)
        }
        
        cloader.addConstraints()
    }
}

