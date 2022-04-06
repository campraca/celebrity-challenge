package org.campraca.domain;

import java.util.List;
import java.util.Objects;

public class Person {

    private final String name;
    private final List<Person> peopleKnown;

    public Person(String name, List<Person> peopleKnown) {
        this.name = name;
        this.peopleKnown = peopleKnown;
    }

    public String getName() {
        return name;
    }

    public List<Person> getPeopleKnown() {
        return peopleKnown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(peopleKnown, person.peopleKnown);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, peopleKnown);
    }
}
