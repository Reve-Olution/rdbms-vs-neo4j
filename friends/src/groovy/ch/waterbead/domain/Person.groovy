package ch.waterbead.domain

class Person {
    long id;
    String name;
    List<Person> friends = [];
    
    def addFriend(Person person) {
        friends.add(person)
    }
}

