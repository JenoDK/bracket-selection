package com.jeno.bracketselectiontest;

public class TestSelections {
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
