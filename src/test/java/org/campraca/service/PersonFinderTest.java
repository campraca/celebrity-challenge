package org.campraca.service;

import org.campraca.domain.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonFinderTest {

    @Test
    void shouldReturnCelebrity() {
        Optional<Person> celebrity = PersonFinder.findCelebrity(buildTeam());

        assertTrue(celebrity.isPresent());
        assertEquals("Brad Pitt", celebrity.get().getName());
        assertTrue(celebrity.get().getPeopleKnown().isEmpty());
    }

    @Test
    void shouldNotReturnCelebrityWhenOneMemberDoesNotKnow() {
        List<Person> people = buildTeam();
        people.set(2, new Person("PersonConflict", List.of(people.get(1))));

        Optional<Person> celebrity = PersonFinder.findCelebrity(people);

        assertFalse(celebrity.isPresent());
    }

    @Test
    void shouldNotReturnCelebrityWhenMoreThanOneMembersDoNotHavePeopleKnown() {
        List<Person> people = buildTeam();
        people.set(2, new Person("Supposed Celebrity", emptyList()));

        Optional<Person> celebrity = PersonFinder.findCelebrity(people);

        assertFalse(celebrity.isPresent());
    }

    @Test
    void shouldNotReturnCelebrityWhenEveryOneKnowsAtLeastOnePerson() {
        List<Person> people = buildTeam();
        people.set(0, new Person("Brad Pitt", List.of(people.get(1))));

        Optional<Person> celebrity = PersonFinder.findCelebrity(people);

        assertFalse(celebrity.isPresent());
    }

    private List<Person> buildTeam() {
        Person celebrity = new Person("Brad Pitt", emptyList());
        Person person1 = new Person("Camilo Andres", List.of(celebrity));
        Person person2 = new Person("Joe Molina", List.of(celebrity, person1));
        Person person3 = new Person("John Test", List.of(person1, person2, celebrity));
        Person person4 = new Person("Clark Kent", List.of(person3, celebrity));

        return Arrays.asList(celebrity, person1, person2, person3, person4);
    }

}