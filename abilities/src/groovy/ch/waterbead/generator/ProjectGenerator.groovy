package ch.waterbead.generator

import ch.waterbead.config.Config
import ch.waterbead.domain.Project
import ch.waterbead.loader.FileLoader
import ch.waterbead.sqlloader.ConnectionManager


class ProjectGenerator implements Generator<Project> {
    @Override
    public java.util.List generate() {
        List<Project> projectGenerated = []
        List projects = FileLoader.load(Config.FILE_PROJECTS)
        projects.eachWithIndex() {
            name, i ->
            long newId = i + 1
            Project project = new Project(id : newId, name : name)
            projectGenerated.add(project)
        }
        return projectGenerated
    }
}

