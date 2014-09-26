package ch.waterbead.neo4jloader

import ch.waterbead.config.Config

/**
 * Orchestrate the loading of the graph database content
*/
class GlobalNeo4Loader {
    def static load(def abilities, def projects, def groups, def employes) {
        AbilityNeo4JLoader aloader = new AbilityNeo4JLoader()
        ProjectNeo4JLoader ploader = new ProjectNeo4JLoader()
        GroupNeo4JLoader gloader = new GroupNeo4JLoader()
        CustomNeo4JLoader cloader = new CustomNeo4JLoader();
        def abilitiesNodes = aloader.load(abilities)
        def projectsNodes = ploader.load(projects)
        def groupsNodes = gloader.load(groups)     
        
        
        EmployeNeo4JLoader eloader = new EmployeNeo4JLoader(groupsNodes, projectsNodes, abilitiesNodes)
        eloader.load(employes)
        
        //cloader.addConstraints()
    }
}

