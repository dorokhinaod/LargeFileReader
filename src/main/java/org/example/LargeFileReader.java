package org.example;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LargeFileReader {
    protected final String TEST_FILE_PATH = "QAtest.txt";
    protected final String SUBSTRING = "a";

    public static void main(String[] args) {
        new LargeFileReader().getTotalCountOfRowsUsingStream();
        new LargeFileReader().getTotalCountOfRowsUsingBufferedReader();
        new LargeFileReader().getCountOfMatchesUsingStream();
        new LargeFileReader().getTotalCountOfMatchesUsingBufferedreader();
    }

    public void getTotalCountOfRowsUsingStream(){
        long total = 0;
        long begin = System.currentTimeMillis();
            try (Stream<String> lines = java.nio.file.Files.lines(Paths.get(TEST_FILE_PATH))) {
                System.out.printf("Count of rows: %s rows", lines.count());
                System.out.println();

                total += System.currentTimeMillis() - begin;

                System.out.printf("Time for counting of rows using Stream: %s ms", total);
                System.out.println();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public void getTotalCountOfMatchesUsingBufferedreader(){
        File file = new File(TEST_FILE_PATH);
        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {
            long total = 0;
            long begin = System.currentTimeMillis();
             String line = br.readLine();
             long totalCountOfMatches = 0;

             while (line != null) {
                 int countOfLineMatches = StringUtils.countMatches(line, SUBSTRING);
                 totalCountOfMatches += countOfLineMatches;
                 line = br.readLine();
             }

            total += System.currentTimeMillis() - begin;
            System.out.println("Total count of matches: %s matches" + totalCountOfMatches);
            System.out.printf("Time for counting of matches using Buffered Reader: %s ms", total);
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getTotalCountOfRowsUsingBufferedReader(){
        File file = new File(TEST_FILE_PATH);
        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {
            long total = 0;
            long begin = System.currentTimeMillis();

            System.out.printf("Total count of rows: %s rows", br.lines().count());
            System.out.println();

            total += System.currentTimeMillis() - begin;

            System.out.printf("Time for counting of matches using Buffered Reader: %s ms", total);
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getCountOfMatchesUsingStream(){
        long total = 0;
        long begin = System.currentTimeMillis();
        try (Stream<String> lines = java.nio.file.Files.lines(Paths.get(TEST_FILE_PATH))) {
            long count = lines.mapToLong(line ->
                    StringUtils.countMatches(line, "a")).sum();
            System.out.printf("Count of substring is: %s pcs.", count);
            System.out.println();

            total += System.currentTimeMillis() - begin;

            System.out.printf("Time for counting of matches using Stream: %s ms", total);
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}