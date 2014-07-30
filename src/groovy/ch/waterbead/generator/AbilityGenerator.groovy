package ch.waterbead.generator

import ch.waterbead.config.Config
import ch.waterbead.domain.Ability
import ch.waterbead.loader.FileLoader

class AbilityGenerator implements Generator<Ability> {
    @Override
    public List<Ability> generate() {
        List<Ability> abilitiesGenerated = []
        List abilities = FileLoader.load(Config.FILE_ABILITIES);
        abilities.eachWithIndex() {
            name, i ->
            long newId = i + 1;
            Ability ability = new Ability(id : newId, name : name)
            abilitiesGenerated.add(ability);
        }
        return abilitiesGenerated;
    }
}

