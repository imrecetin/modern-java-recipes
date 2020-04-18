package org.modernjavarecipes.fileInputOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class RetrievingFilesAsAStreamDemo {
    public static void main(String[] args) {
        //Using Files.list(path)
        try (Stream<Path> list = Files.list(Paths.get("src/main/java"))) {
            list.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //When run on the source code for this book, the result includes both directories and
        //individual files:
    }
}
