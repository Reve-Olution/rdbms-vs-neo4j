package ch.waterbead.loader

import ch.waterbead.config.Config


class FileLoader {
    private static cacheFile = [:]
    
    static List load(String filename) {
        List dataInCache = searchInCache(filename)
        if(dataInCache==null) {
            def data = []
            def filedata = ClassLoader.getSystemResource(filename)
            filedata.eachLine { value ->
                data.add(value)
            }
            putInCache(filename, data)
        }
        return searchInCache(filename)
    }
    
    private static searchInCache(String filename) {
        return cacheFile[filename]
    }
    
    private static putInCache(String filename, List data) {
        cacheFile[filename] = data;
    }
}

