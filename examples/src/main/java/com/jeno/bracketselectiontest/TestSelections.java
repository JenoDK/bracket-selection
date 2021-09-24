package com.jeno.bracketselectiontest;

public class TestSelections {

    public static final String LONG_STRING = "Hey I am a very long string and I am here for testing purposes";

    public static void main(String[] args) {
        methodWithMultiLineParameters("hey", "I", "am", "multiline");
    }

    private static void methodWithMultiLineParameters(
            String hey, 
            String i,
            String am,
            String multiline) {
        System.err.println(hey + " " + i + " " + am + " " + multiline);
    }
}
