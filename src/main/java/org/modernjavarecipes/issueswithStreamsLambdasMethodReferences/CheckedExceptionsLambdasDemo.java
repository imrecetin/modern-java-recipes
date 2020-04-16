package org.modernjavarecipes.issueswithStreamsLambdasMethodReferences;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CheckedExceptionsLambdasDemo {

    public static void main(String[] args) {

    }

    //URL encoding a collection of strings (NOTE: DOES NOT COMPILE)
    public List<String> encodeValues(String... values) {
        return Arrays.stream(values)
                .map(s -> URLEncoder.encode(s, "UTF-8")).collect(Collectors.toList());
    }

    //Declaring the exception (ALSO DOES NOT COMPILE)
    public List<String> encodeValues(String... values) throws UnsupportedEncodingException {
        return Arrays.stream(values)
                .map(s -> URLEncoder.encode(s, "UTF-8")).collect(Collectors.toList());
    }

    //URL encoding using try/catch (CORRECT)
    public List<String> encodeValuesAnonInnerClass(String... values) {
        return Arrays.stream(values)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        try {
                            return URLEncoder.encode(s, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            return "";
                        }
                    }
                })
                .collect(Collectors.toList());
    }

    public List<String> encodeValuesWithCatchingException(String... values) {
        return Arrays.stream(values)
                .map(s -> {
                    try {
                        return URLEncoder.encode(s, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .collect(Collectors.toList());
    }

    //URL encoding delegating to a method
    private String encodeString(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> encodeValuesUsingMethod(String... values) {
        return Arrays.stream(values)
                .map(this::encodeString)
                .collect(Collectors.toList());
    }


}
