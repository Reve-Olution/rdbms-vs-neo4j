package ch.waterbead.generator

import ch.waterbead.domain.Group
import ch.waterbead.loader.FileLoader
import ch.waterbead.config.Config

class GroupGenerator implements Generator<Group> {
    @Override
    public List<Group> generate() {
        List<Group> groupsGenerated = []
        List groups = FileLoader.load(Config.FILE_GROUPS);
        groups.eachWithIndex { name, i ->
            int newId = i + 1;
            Group group = new Group(id: newId, name: name)
            groupsGenerated.add(group)
        }
        return groupsGenerated
    }
}

