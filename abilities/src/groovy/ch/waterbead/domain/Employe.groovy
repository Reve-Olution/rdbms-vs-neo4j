package ch.waterbead.domain

class Employe {
    long id
    String firstname
    String lastname
    int yearOfBirthDay
    List<Project> projects = []
    List<Group> groups = []
    List<Ability> abilities = []
    
    def addAbility(long idAbility, int score) {
        Ability ability = new Ability()
        ability.id = idAbility
        ability.score = score
        abilities.add(ability)
    }
    
    def addProject(long idProject) {
        Project project = new Project()
        project.id = idProject
        projects.add(project)
    }
    
    def addGroup(long idGroup) {
        Group group = new Group()
        group.id = idGroup
        groups.add(group)
    }
}

