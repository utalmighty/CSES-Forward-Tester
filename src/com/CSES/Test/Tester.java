package com.CSES.Test;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class Tester {

    private static final String SEPERATOR = "/";
    private static final String INPUT = ".in";
    private static final String OUTPUT = ".out";
    private static String ARTIFACT_FILE = "path"; // artifact output file

    public static void main(String[] args) throws IOException {
        String dir = ""; // Test folder path
        CSES solution; // Replace with solution
        runTests(dir, solution);
    }

    private static void runTests(String dir, CSES solution) throws IOException {
        String path = dir + SEPERATOR;
        int numberOfTests = numberOfTests(dir);
        for (int test=1; test <= numberOfTests; test++) {
            String intputFile = test + INPUT;
            clearFileContents(ARTIFACT_FILE);
            runUserSolution(solution, path + intputFile, ARTIFACT_FILE);
            String outputFile = test + OUTPUT;
            compareOutput(test, path + outputFile, ARTIFACT_FILE);
        }
    }

    private static void clearFileContents(String file) throws IOException {
        FileChannel.open(Paths.get(file), StandardOpenOption.WRITE).truncate(0).close();
    }

    private static void runUserSolution(CSES solution, String inputFile, String userOutputFile) throws IOException {
        PrintStream console = System.out;
        PrintStream file = new PrintStream(new File(userOutputFile));
        printTo(file);
        solution.solution(getReader(inputFile));
        printTo(console);
    }

    private static void printTo(PrintStream stream) {
        System.setOut(stream);
    }
 
    private static BufferedReader getReader(String file) throws FileNotFoundException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        return new BufferedReader(reader);
    }
        
    private static int numberOfTests(String dir) {
        int files = new File(dir).listFiles().length;
        return files/2;
    }

    private static void compareOutput(int testNumber, String outputFile, String actualFile) throws FileNotFoundException, IOException {
        System.out.print("Test: " + testNumber + " ");
        System.out.print("Expected: " + expected + " ");
        String expected = getReader(outputFile).readLine();
        String actual = getReader(actualFile).readLine();
        System.out.print("Actual: ");
        if (expected.equals(actual)) {
            System.out.println(actual);
        } else {
            System.err.println(actual);
        }
    }
}