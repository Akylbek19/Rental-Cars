package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public static void saveToCsv(List<String[]> data, String fileName) {
        try (PrintWriter writer = new PrintWriter("data/" + fileName)) {
            for (String[] row : data) {
                writer.println(String.join(",", row));
            }
        } catch (IOException e) {
            System.err.println("Saving error: " + e.getMessage());
        }
    }

    public static List<String[]> loadFromCsv(String fileName) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("data/" + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("The file was not found, a new one will be created.");
        }
        return data;
    }
}