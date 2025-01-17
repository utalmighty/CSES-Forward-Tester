package com.CSES.Test;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

public class Tester {

    private static final String SEPERATOR = "/";
    private static final String INPUT = ".in";
    private static final String OUTPUT = ".out";
    private static String ARTIFACT_FILE = "path"; // artifact output file

    public static void main(String[] args) throws IOException {
        String dir = ""; // Test folder path
        CSES solution; // Replace with solution class
        runTests(dir, solution);
    }

    private static void runTests(String dir, CSES solution) throws IOException {
        String path = dir + SEPERATOR;
        int numberOfTests = numberOfTests(dir);
        for (int test=1; test <= numberOfTests; test++) {
            String intputFile = path + test + INPUT;
            clearFileContents(ARTIFACT_FILE);
            long start = System.currentTimeMillis();
            runUserSolution(solution, intputFile, ARTIFACT_FILE);
            long timeTakenInMillis = System.currentTimeMillis() - start;
            String outputFile = path + test + OUTPUT;
            System.out.print("Test: "+ test + "/" + numberOfTests + " ");
            if (compareOutput(outputFile, ARTIFACT_FILE)) {
                System.out.println("✅ ("+ timeTakenInMillis +"ms)");
            }
            else {
                System.out.println("❌ (" + timeTakenInMillis + "ms)");
                System.out.println("Input: ");
                printContentOfFile(intputFile);
                System.out.println("Expected: ");
                printContentOfFile(outputFile);
                System.err.println("Actual: ");
                printContentOfFile(ARTIFACT_FILE);
                return;
            }
        }
        System.out.println("ACCEPTED!");
    }

    private static void printContentOfFile(String file) throws FileNotFoundException {
        Stream<String> content = getReader(file).lines();
        content.forEach(Sytem.out::println);
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

    private static boolean compareOutput(int testNumber, String outputFile, String actualFile) throws FileNotFoundException, IOException {
        Stream<String> expectedOutput = getReader(outputFile).lines();
        Stream<String> actualOutput = getReader(actualFile).lines();
        Iterator<String> iter1 = expectedOutput.iterator(), iter2 = actualOutput.iterator();
        while (iter1.hasNext() && iter2.hasNext()) {
            if (!iter1.next.equals(iter2.next())) 
                return false;
        }
        return (iter1.hasNext() == iter2.hasNext());
    }
}