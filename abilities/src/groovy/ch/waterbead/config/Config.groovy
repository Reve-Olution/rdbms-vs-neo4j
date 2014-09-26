package ch.waterbead.config

class Config {
    static final DEBUG = true;
    
    static final int population = 100000;
    
    /** Population files */
    static final String FILE_FIRST_NAMES = 'firstnames.csv'
    static final String FILE_LAST_NAMES = 'lastnames.csv'
    static final String FILE_ABILITIES = 'abilities.csv'
    static final String FILE_GROUPS = 'groups.csv'
    static final String FILE_PROJECTS = 'projects.csv'
    
    /** Maximum score for an ability */
    static final MAX_SCORE = 100
    
   /** Maximum number of groups for a person */
   static final MAX_GROUPS = 3
   
   /** Maximum number of abilities for a person */
   static final MAX_ABILITIES = 2
   
   /** Maximum number of projects for a person */
   static final MAX_PROJECTS = 2
   
   /** Maximum birthday date for a person */
   static final MIN_BIRTHDAY_YEAR = 1950
   static final MAX_BIRTHDAY_YEAR = 1995
}

