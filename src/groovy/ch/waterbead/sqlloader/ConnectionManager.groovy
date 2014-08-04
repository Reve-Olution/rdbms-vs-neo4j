package ch.waterbead.sqlloader

import ch.waterbead.config.Config
import groovy.sql.Sql;

class ConnectionManager {
    static Sql sql = null;
    
    static synchronized Sql getSql() {
        if(sql==null) {
            sql = Sql.newInstance(Config.rdbmsUrl,Config.rdbmsUser,Config.rdbmsPassword,Config.rdbmsDriver)    
            sql.getConnection().setAutoCommit(false);        
        }
        return sql;
    }
}

