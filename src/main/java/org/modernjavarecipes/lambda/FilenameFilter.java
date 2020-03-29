package org.modernjavarecipes.lambda;

import java.io.File;
import java.util.Arrays;

public class FilenameFilter {

    public static void main(String[] args) {
        //An anonymous inner class implementation of FilenameFilter
        File directory1=new File("./src/main/java");
        String[] fileNames = directory1.list(new java.io.FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        System.out.println(Arrays.asList(fileNames));

        //Lambda expression implementing FilenameFilter
        File directory2 = new File("./src/main/java");
        String[] names1 = directory2.list((dir, name) -> name.endsWith(".java"));
        System.out.println(Arrays.asList(names1));

        //Lambda expression with explicit data types
        File directory3 = new File("./src/main/java");
        String[] names2 = directory3.list((File dir, String name) -> name.endsWith(".java"));
        System.out.println(Arrays.asList(names2));

        //A block lambda
        File directory4 = new File("./src/main/java");
        String[] names3 = directory4.list((File dir, String name) -> {
            return name.endsWith(".java");
        });
        System.out.println(Arrays.asList(names3));

    }

}
