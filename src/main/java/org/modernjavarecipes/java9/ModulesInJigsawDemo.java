package org.modernjavarecipes.java9;

public class ModulesInJigsawDemo {

    /*the concept of modules changes the nature of public and private. If a
    module does not export a particular package, you can’t access classes inside it even if
    they are made public. Likewise, you can no longer use reflection to access nonpublic members of a class that isn’t in an exported package. This affects reflectionbased libraries and frameworks (including popular ones like Spring and Hibernate),
    as well as virtually every non-Java language on the JVM. As a concession, the team
    has proposed that a command-line flag called --illegal-access=permit will be the
    default in Java 9 and disallowed in a future release.*/
}
