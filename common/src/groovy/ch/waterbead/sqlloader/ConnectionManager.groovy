package ch.waterbead.sqlloader

import groovy.sql.Sql;

class ConnectionManager {
    private static Sql sql = null;
    
    static synchronized Sql getSql() {
        return sql;
    }
    
    static synchronized initSQL(def url, def user, def password, def driver) {
        if(sql!=null)
            throw new IllegalAccessException("Init SQL has already called")
        sql = Sql.newInstance(url,user,password,driver)    
        sql.getConnection().setAutoCommit(false);             
    }
}

