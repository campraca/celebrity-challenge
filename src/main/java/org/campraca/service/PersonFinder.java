package org.campraca.service;

import org.campraca.domain.Person;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonFinder {

    private final static Logger log = Logger.getLogger(PersonFinder.class.getName());

    /**
     * Method to find the celebrity into a group of people
     * A celebrity is a person known by everyone but he/she does not know anybody.
     *
     * @param team
     * @return
     */
    public static Optional<Person> findCelebrity(List<Person> team) {
        log.log(Level.INFO, "Finding the celebrity");

        Optional<Person> celebrityOpt = team.stream()
                .filter(person -> person.getPeopleKnown().isEmpty()).findAny();

        if (!celebrityOpt.isPresent()) {
            log.log(Level.WARNING, "Everyone knows at least one person");
        }

        return celebrityOpt.filter(celebrity -> team.stream()
                .filter(person -> person != celebrity)
                .allMatch(person -> person.getPeopleKnown().contains(celebrity)));
    }
}
