package com.example.csv_import;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * CSV Import Example
 *
 */
public class CsvImport {
    public static void main(String[] args) {
        String fileName1 = "../iris.csv";
        String fileName2 = "../iris-tabs.csv";
        try (CSVReader reader = new CSVReader(new FileReader(fileName1))) {
            List<String[]> data1 = reader.readAll();
            System.out.println("CSV-DATEI 1:");
            System.out.println(Arrays.toString(data1.get(0)));
            System.out.println(Arrays.toString(data1.get(50)));
            System.out.println(Arrays.toString(data1.get(100)));
        } catch(CsvException e) {
            System.out.println("CSV-Fehler: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("Dateifehler: " + e.getMessage());
        }
        CSVParser csvParser = new CSVParserBuilder().withSeparator('\t').build();
        try(CSVReader reader = new CSVReaderBuilder(
          new FileReader(fileName2))
          .withCSVParser(csvParser)
          .withSkipLines(1)
          .build()) {
            List<String[]> data2 = reader.readAll();
            System.out.println("CSV-DATEI 2:");
            System.out.println(Arrays.toString(data2.get(0)));
            System.out.println(Arrays.toString(data2.get(50)));
            System.out.println(Arrays.toString(data2.get(100)));
        } catch(CsvException e) {
            System.out.println("CSV-Fehler: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("Dateifehler: " + e.getMessage());
        }
    }
}
