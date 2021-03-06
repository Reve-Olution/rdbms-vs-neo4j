package ch.waterbead.main

import ch.waterbead.config.Config
import ch.waterbead.config.ConfigLoader
import ch.waterbead.domain.Ability
import ch.waterbead.domain.Employe
import ch.waterbead.generator.*
import ch.waterbead.generator.AbilityGenerator
import ch.waterbead.generator.EmployeGenerator
import ch.waterbead.generator.GroupGenerator
import ch.waterbead.generator.ProjectGenerator
import ch.waterbead.loader.FileLoader
import ch.waterbead.neo4jloader.AbilityNeo4JLoader
import ch.waterbead.neo4jloader.EmployeNeo4JLoader
import ch.waterbead.neo4jloader.GlobalNeo4Loader
import ch.waterbead.neo4jloader.GroupNeo4JLoader
import ch.waterbead.neo4jloader.Neo4JConnectionManager
import ch.waterbead.neo4jloader.ProjectNeo4JLoader
import ch.waterbead.sqlloader.*
import ch.waterbead.sqlloader.Cleaner
import ch.waterbead.sqlloader.GlobalSQLLoader
import groovy.sql.Sql

def config = ConfigLoader.load();

AbilityGenerator ag = new AbilityGenerator()
ProjectGenerator pg = new ProjectGenerator()
GroupGenerator gg = new GroupGenerator()
EmployeGenerator eg = new EmployeGenerator(config)

def abilities = ag.generate()
def projects = pg.generate()
def groups = gg.generate()
def employes = eg.generate()

if(config.rdbms.url) {
    ConnectionManager.initSQL(config)
    GlobalSQLLoader.load(abilities, projects, groups, employes)
}
if(config.neo4j.path) {
    Neo4JConnectionManager.initGraphDatabase(config.neo4j.path)
    GlobalNeo4Loader.load(abilities, projects, groups, employes)
    Neo4JConnectionManager.finish();
}