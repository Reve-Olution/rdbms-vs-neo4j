package ch.waterbead.config

import ch.waterbead.neo4jloader.Neo4JConnectionManager
import ch.waterbead.sqlloader.ConnectionManager
import groovy.util.ConfigSlurper


class ConfigLoader {
    static final BASE_CONFIG_PROPERTIES = "config/base_config.properties"
    static final CONFIG_PROPERTIES = "config.properties"
    
    static final DOCUMENTATION = "https://github.com/Reve-Olution/RDBMS-vs-Neo4J"
    
    static def load() {
        File configProperties = new File(CONFIG_PROPERTIES)
        def url = getClassLoader().getSystemResource(BASE_CONFIG_PROPERTIES)
        def baseConfig = new ConfigSlurper().parse(url)
        def config = null;
        
        String currentDirectory = new File(".").canonicalPath
        
        if(!configProperties.exists()) {
            println "No ${CONFIG_PROPERTIES} defined on the directory ${currentDirectory}. Do not forget to create it and rerun the application according to the documentation"
            println("Documentation location : ${DOCUMENTATION}")
            quit()
        } else {
            println "Configuration loaded from ${currentDirectory}${CONFIG_PROPERTIES}"
            def userConfig = new ConfigSlurper().parse(configProperties.toURL())
            config = baseConfig.merge(userConfig);
        }

        if(!config.rdbms.url && !config.neo4j.path) {
            println "Please defined an 'rdbms.url' or/and a 'neo4j.path' on the config.properties"
            quit()
        }
        
        if(!config.rdbms.url) {
            println "'rdbms.url' not defined, no RDBMS database is going to be populate"
        }
        
        if(!config.neo4j.path) {
            println "'neo4j.path' not defined, no Neo4J database is going to be populate"
        }

        if(!config.population) {
            println "No 'population' defined, the default value of ${config.population}  will be used"
        }
        
        return config;
    }
    
    static def quit() {
        System.exit(0)
    }
}

    