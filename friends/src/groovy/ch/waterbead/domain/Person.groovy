package ch.waterbead.domain

class Person {
    long id;
    String name;
    List<Long> friends = [];
    
    def addFriend(Long idFriend) {
        friends.add(idFriend)
    }
}

