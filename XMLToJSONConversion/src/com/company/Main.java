package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File entryFile = new File("input.txt");
        List<String> allFileLines = null;

        try {
            allFileLines = readTheFile(entryFile);
        } catch (Exception ex) {
            System.out.println("Error reading the file.");
            return;
        }

        String convertedJson = null;

        try {
            String allLines = "";

            for (String line : allFileLines) {
                allLines += line.trim();
            }

            XMLParsing xmlParsing = new XMLParsing();
            XML xmlContent = xmlParsing.parseXML(allLines);
            JSON jsonElement = new JSON();

            convertedJson = jsonElement.convertJSON(xmlContent);
        } catch (Exception ex) {
            System.out.println("Error loading the file.");
        }

        if (convertedJson != null) {
            try {
                writeNewFile(convertedJson, "output.txt");
            } catch (Exception ex) {}
        }
    }

    private static List<String> readTheFile(File file) throws FileNotFoundException {
        Scanner reader = null;

        try {
            reader = new Scanner(file);
            List<String> result = new ArrayList<String>();

            while (reader.hasNextLine()) {
                result.add(reader.nextLine());
            }

            return result;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static void writeNewFile(String json, String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write(json);
        writer.flush();
        writer.close();
    }

}
