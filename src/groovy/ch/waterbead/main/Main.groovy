package ch.waterbead.main

import ch.waterbead.config.Config
import ch.waterbead.domain.Employe
import ch.waterbead.generator.*
import ch.waterbead.loader.FileLoader
import ch.waterbead.neo4jloader.AbilityNeo4JLoader
import ch.waterbead.neo4jloader.EmployeNeo4JLoader
import ch.waterbead.neo4jloader.GroupNeo4JLoader
import ch.waterbead.neo4jloader.Neo4JConnectionManager
import ch.waterbead.neo4jloader.ProjectNeo4JLoader
import ch.waterbead.sqlloader.*
import ch.waterbead.sqlloader.Cleaner
import ch.waterbead.sqlloader.ConnectionManager
import groovy.sql.Sql

//Cleaner.clean()

//EmployeSQLLoader eloader = new EmployeSQLLoader();
//AbilitySQLLoader aloader = new AbilitySQLLoader();
//ProjectSQLLoader ploader = new ProjectSQLLoader();
//GroupSQLLoader gloader = new GroupSQLLoader();

println "Neo4JLoader"

AbilityNeo4JLoader aloader = new AbilityNeo4JLoader()
ProjectNeo4JLoader ploader = new ProjectNeo4JLoader()
GroupNeo4JLoader gloader = new GroupNeo4JLoader()
EmployeNeo4JLoader eloader = new EmployeNeo4JLoader()

EmployeGenerator eg = new EmployeGenerator();
AbilityGenerator ag = new AbilityGenerator();
ProjectGenerator pg = new ProjectGenerator();
GroupGenerator gg = new GroupGenerator();


Neo4JConnectionManager.beginTransaction();

aloader.load(ag.generate())
ploader.load(pg.generate())
gloader.load(gg.generate())
eloader.load(eg.generate())

//ConnectionManager.getSql().commit()

Neo4JConnectionManager.commitTransaction();