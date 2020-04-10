package org.modernjavarecipes.comparatorsandcollectors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertingStreamintoCollectionDemo {

    public static void main(String[] args) {
        //Creating a List
        List<String> superHeroes =
                Stream.of("Mr. Furious", "The Blue Raja", "The Shoveler",
                        "The Bowler", "Invisible Boy", "The Spleen", "The Sphinx")
                        .collect(Collectors.toList());

        //Creating a Set
        Set<String> villains =
                Stream.of("Casanova Frankenstein", "The Disco Boys",
                        "The Not-So-Goodie Mob", "The Suits", "The Suzies",
                        "The Furriers", "The Furriers")
                        .collect(Collectors.toSet()); //Duplicate name, removed when converting to a Set

        //Creating a linked list
        List<String> actors =
                Stream.of("Hank Azaria", "Janeane Garofalo", "William H. Macy",
                        "Paul Reubens", "Ben Stiller", "Kel Mitchell", "Wes Studi")
                        .collect(Collectors.toCollection(LinkedList::new));

        //Creating an array
        String[] wannabes =
                Stream.of("The Waffler", "Reverse Psychologist", "PMS Avenger")
                        .toArray(String[]::new);

        //Creating a Map
        Set<Actor> actorSet = new HashSet<Actor>();
        Map<String, String> actorMap = actorSet.stream()
                .collect(Collectors.toMap(Actor::getName, Actor::getRole));
        actorMap.forEach((key,value) ->
                System.out.printf("%s played %s%n", key, value));

    }
    public static class Actor{
        private String name;
        private String role;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
