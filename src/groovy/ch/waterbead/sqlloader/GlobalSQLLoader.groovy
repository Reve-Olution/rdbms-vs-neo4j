package ch.waterbead.sqlloader

class GlobalSQLLoader {
    def static load(def abilities, def projects, def groups, def employes) {
        Cleaner.clean()

        AbilitySQLLoader aloader = new AbilitySQLLoader();
        ProjectSQLLoader ploader = new ProjectSQLLoader();
        GroupSQLLoader gloader = new GroupSQLLoader();
        EmployeSQLLoader eloader = new EmployeSQLLoader();
        
        aloader.load(abilities)
        ploader.load(projects)
        gloader.load(groups)
        eloader.load(employes)
        
        ConnectionManager.sql.connection.commit()
    }
}

