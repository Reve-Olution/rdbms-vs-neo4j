package ch.waterbead

import ch.waterbead.generator.PersonGenerator
import ch.waterbead.sqlloader.PersonSQLLoader
import ch.waterbead.config.ConfigLoader

ConfigLoader.load()

def personGenerator = new PersonGenerator()

def personnes = personGenerator.generate()
def personSQLLoader = new PersonSQLLoader();
personSQLLoader.load(personnes);
