package ch.waterbead.sqlloader

import groovy.sql.Sql;

class ConnectionManager {
    private static Sql sql = null;
    
    static synchronized Sql getSql() {
        return sql;
    }
    
    static synchronized initSQL(def config) {
        if(sql!=null)
            throw new IllegalAccessException("Init SQL has already called")
        sql = Sql.newInstance(config.rdbms.url,config.rdbms.user,config.rdbms.password,config.rdbms.driver)    
        sql.getConnection().setAutoCommit(false);             
    }
}

