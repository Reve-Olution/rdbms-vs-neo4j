package ch.waterbead.config

enum LoaderType {
	NEO4J,
        MARIADB;
        
    boolean isSQLConnection() {
        switch(this) {
        case MARIADB:
            return true;
        }
        return false;
    }
}

