package ch.waterbead.config

import ch.waterbead.neo4jloader.Neo4JConnectionManager
import ch.waterbead.sqlloader.ConnectionManager

class ConfigLoader {
    /** Generation parameters */
    static int population = 1000;
    
    /** Flags that determine which databases have to be populated */
    static boolean mustNeo4JPopulated = false
    static boolean mustRDBMSPopulated = false;
    
    static final RDBMS_USER = "rdbmsUser"
    static final RDBMS_PASSWORD = "rdbmsPassword"
    static final RDBMS_URL = "rdbmsUrl"
    static final RDBMS_DRIVER = "rdbmsDriver"
    
    static final NEO4J_PATH = "neo4jPath"
    
    static final POPULATION = "population"
    
    static final CONFIG_PROPERTIES = "config.properties"
    
    static final DOCUMENTATION = "http://localhost.ch"
    
    static def load() {
        Properties properties = new Properties()
        File file = new File(CONFIG_PROPERTIES)
        String configPath = file.absolutePath
        String currentDirectory = new File("").absolutePath
        
        if(!file.exists()) {
            println("No ${CONFIG_PROPERTIES} defined on the directory ${configPath}. Do not forget to create it and rerun the application according to the documentation")
            println("Documentation location ${DOCUMENTATION}")
            quit()
        } else {
            println('Loading poperties from ' + configPath)
                file.withInputStream() {
                    is -> properties.load(is)
                }
        }

        def rdbmsUrl = properties.get(RDBMS_URL)
        def rdbmsUser = properties.get(RDBMS_USER)
        def rdbmsPassword = properties.get(RDBMS_PASSWORD)
        def rdbmsDriver = properties.get(RDBMS_DRIVER)
        def neo4jPath = properties.get(NEO4J_PATH)
        def population = Integer.valueOf(properties.get(POPULATION))

        if(!rdbmsUrl && !neo4jPath) {
            println "Please defined an '${RDBMS_URL}' or/and a '${NEO4J_PATH}' on the config.properties"
            quit()
        }
        if(!rdbmsUrl) {
            println "${RDBMS_URL} not defined, no RDBMS database is going to be popoulate"
        } else {
            mustRDBMSPopulated = true
            initConnectionManager(rdbmsUrl, rdbmsUser, rdbmsPassword, rdbmsDriver)
        }
        if(!neo4jPath) {
            println "${NEO4J_PATH} not defined, no Neo4J database is going to be populate"
        } else {
            mustNeo4JPopulated = true
            initGraphDaatabase(neo4jPath)
        }

        if(!population) {
            println "No '${POPULATION}' defined, the default value of ${population}  will be used"
        } else {
            population = population
        }
    }
    
    static def quit() {
        System.exit(0)
    }
    
    static def initConnectionManager(def url, def user, def password, def driver) {
        ConnectionManager.initSQL(url, user, password, driver)
    }
    
    static def initGraphDaatabase(def neo4JPath) {
        Neo4JConnectionManager.initGraphDatabase(neo4JPath)
    }
}

    