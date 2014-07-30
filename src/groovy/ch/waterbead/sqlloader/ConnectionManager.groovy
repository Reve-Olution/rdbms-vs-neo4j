package ch.waterbead.sqlloader

import groovy.sql.Sql;

class ConnectionManager {
    //TODO: Should be a parameter
    static final Sql sql = Sql.newInstance("jdbc:mariadb://localhost:3306/population?createDatabaseIfNotExist=true",'root','','org.mariadb.jdbc.Driver')
    static {
        sql.getConnection().setAutoCommit(false);
    }
    static Sql getSql() {
        return sql;
    }
}

