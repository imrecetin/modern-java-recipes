package org.modernjavarecipes.fileInputOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class WalkingTheFilesystemDemo {
    public static void main(String[] args) {
        //The return type is a lazily populated Stream of Path instances obtained by
        //walking the filesystem from the starting path, performing a depth-first traversal.
        //Walking the tree
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/java"))) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
