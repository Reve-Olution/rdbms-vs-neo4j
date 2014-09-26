package ch.waterbead.generator

import ch.waterbead.config.Config
import ch.waterbead.domain.Employe
import ch.waterbead.loader.FileLoader

class EmployeGenerator implements Generator<Employe> {
    private def config;
    
    EmployeGenerator(def config) {
        this.config = config;
    }
    @Override
    public List generate() {
        List employes = []
        List firstnames = FileLoader.load(Config.FILE_FIRST_NAMES)
        List lastnames = FileLoader.load(Config.FILE_LAST_NAMES)
        List abilities = FileLoader.load(Config.FILE_ABILITIES)
        List groups = FileLoader.load(Config.FILE_GROUPS)
        List projects = FileLoader.load(Config.FILE_PROJECTS)

        config.population.times {
            no ->
            
            int id = no+1
            int randomFirstname = RandomGenerator.get(firstnames.size())
            int randomLastname = RandomGenerator.get(lastnames.size())
            int randomYear = RandomGenerator.get(Config.MIN_BIRTHDAY_YEAR, Config.MAX_BIRTHDAY_YEAR)
            Employe employe = new Employe(id : id, firstname : firstnames[randomFirstname], lastname : lastnames[randomLastname], yearOfBirthDay :  randomYear);
            
            int randomNumberOfAbilities = RandomGenerator.getForIndexOf0(Config.MAX_ABILITIES)
            randomNumberOfAbilities.times {
                int randomAbility = RandomGenerator.getForIndexOf0(abilities.size())
                int randomScoreForAbility = RandomGenerator.get(Config.MAX_SCORE)
                employe.addAbility(randomAbility, randomScoreForAbility)
            }
            
            int randomNumberOfGroups = RandomGenerator.getForIndexOf0(Config.MAX_GROUPS)
            randomNumberOfGroups.times {
                int randomGroup = RandomGenerator.getForIndexOf0(groups.size())
                employe.addGroup(randomGroup)
            }
            
            int randomNumberOfProjects = RandomGenerator.getForIndexOf0(Config.MAX_PROJECTS)
            randomNumberOfProjects.times {
                int randomProject = RandomGenerator.getForIndexOf0(projects.size())
                employe.addProject(randomProject)
            }
                
            employes.add(employe);
        }
        return employes
    }
}

