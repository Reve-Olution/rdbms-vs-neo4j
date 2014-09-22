package ch.waterbead.sqlloader

import groovy.sql.Sql

abstract class SQLLoader {
    protected Sql sql;

    SQLLoader() {
        sql = ConnectionManager.getSql();
    }
}

