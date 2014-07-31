package ch.waterbead.config

class ConfigLoader {
    static def load() {

        Properties properties = new Properties()
        File file = new File("config.properties")
        if(!file.exists()) {
            println('No "config.properties" defined. Do not forget to create it and rerun the application according to the documentation')
            println('Documentation location http://localhost.ch/')
            System.exit(0)
        } else {
            println('Loading poperties from ' + file.absolutePath)
                file.withInputStream() {
                is ->
                properties.load(is)
               }
        }

        def rdbmsUrl = properties.get("rdbmsUrl")
        def rdbmsUser = properties.get("rdbmsUser")
        def rdbmsPassword = properties.get("rdbmsPassword")
        def rdbmsDriver = properties.get("rdbmsDriver")
        def neo4jPath = properties.get("neo4jPath")
        def population = properties.get("population")

        if(rdbmsUrl==null && neo4jPath == null) {
            println 'Please defined an "rdbmsUrl" or/and a "neo4JPath" on the config.properties'
            System.exit(0);
        }
        if(rdbmsUrl==null) {
            println "rbmsUrl not defined, no RDBMS database is going to be popoulate"
        } else {
            Config.rdbmsUrl = rdbmsUrl;
            Config.rdbmsUser = rdbmsUser;
            Config.rdbmsPassword = rdbmsPassword;
            Config.rdbmsDriver = rdbmsDriver;
            println rdbmsUser
            Config.mustRDBMSPopulated = true
        }
        if(neo4jPath==null) {
            println "neo4jPath not defined, no Neo4J database is going to be populate"
        } else {
            Config.neo4JPath = neo4jPath
            Config.mustNeo4JPopulated = true
        }

        if(population==null) {
            println 'No "population" defined, the default value of ' + Config.population + ' will be used'
        }
    }
}

