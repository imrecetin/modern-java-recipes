package org.modernjavarecipes.java9;

import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreatingImmutableCollectionsDemo {
    public static void main(String[] args) {
        //You want to create immutable lists, sets, or maps in Java 9.
        /* They are structurally immutable. Elements cannot be added, removed, or
            replaced. Calling any mutator method will always cause UnsupportedOperation
            Exception to be thrown. However, if the contained elements are themselves
            mutable, this may cause the List’s contents to appear to change.
            • They disallow null elements. Attempts to create them with null elements result in
            NullPointerException.
            • They are serializable if all elements are serializable.
            • The order of elements in the list is the same as the order of the provided argu‐
            ments, or of the elements in the provided array.
            • They are serialized as specified on the Serialized Form page. */

    }

    //A trivial class that holds a mutable value
    public class Holder {
        private int x;
        public Holder(int x) { this.x = x; }
        public void setX(int x) {
            this.x = x;
        }
        public int getX() {
            return x;
        }
    }

    //Changing the wrapped integer
    @Test
    public void areWeImmutableOrArentWe() throws Exception {
        List<Holder> holders = List.of(new Holder(1), new Holder(2));
        assertEquals(1, holders.get(0).getX());
        holders.get(0).setX(4);
        assertEquals(4, holders.get(0).getX());
    }

    //Demonstrating immutability
    @Test(expected = UnsupportedOperationException.class)
    public void showImmutabilityAdd() throws Exception {
        List<Integer> intList = List.of(1, 2, 3);
        intList.add(99);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void showImmutabilityClear() throws Exception {
        List<Integer> intList = List.of(1, 2, 3);
        intList.clear();
    }
    @Test(expected = UnsupportedOperationException.class)
    public void showImmutabilityRemove() throws Exception {
        List<Integer> intList = List.of(1, 2, 3);
        intList.remove(0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void showImmutabilityReplace() throws Exception {
        List<Integer> intList = List.of(1, 2, 3);
        intList.replaceAll(n -> -n);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void showImmutabilitySet() throws Exception {
        List<Integer> intList = List.of(1, 2, 3);
        intList.set(0, 99);
    }

    //Immutable map from entries
    @Test
    public void immutableMapFromEntries() throws Exception {
        Map<String, String> jvmLanguages = Map.ofEntries(
                Map.entry("Java", "http://www.oracle.com/technetwork/java/index.html"),
                Map.entry("Groovy", "http://groovy-lang.org/"),
                Map.entry("Scala", "http://www.scala-lang.org/"),
                Map.entry("Clojure", "https://clojure.org/"),
                Map.entry("Kotlin", "http://kotlinlang.org/"));
        Set<String> names = Set.of("Java", "Scala", "Groovy", "Clojure", "Kotlin");
        List<String> urls = List.of("http://www.oracle.com/technetwork/java/index.html",
                "http://groovy-lang.org/",
                "http://www.scala-lang.org/",
                "https://clojure.org/",
                "http://kotlinlang.org/");
        Set<String> keys = jvmLanguages.keySet();
        Collection<String> values = jvmLanguages.values();
        names.forEach(name -> assertTrue(keys.contains(name)));
        urls.forEach(url -> assertTrue(values.contains(url)));
        Map<String, String> javaMap = Map.of("Java",
                "http://www.oracle.com/technetwork/java/index.html",
                "Groovy",
                "http://groovy-lang.org/",
                "Scala",
                "http://www.scala-lang.org/",
                "Clojure",
                "https://clojure.org/",
                "Kotlin",
                "http://kotlinlang.org/");
        javaMap.forEach((name, url) -> assertTrue(jvmLanguages.keySet().contains(name) && jvmLanguages.values().contains(url)));
    }
}
