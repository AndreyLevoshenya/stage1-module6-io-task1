package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try(FileInputStream fileReader = new FileInputStream(file)) {
            int ch;
            while ((ch = fileReader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            String[] lines = stringBuilder.toString().split("\n");
            String regex = ": ";
            String name = lines[0].trim().split(regex)[1];
            Integer age = Integer.parseInt(lines[1].trim().split(regex)[1]);
            String email = lines[2].trim().split(regex)[1];
            Long phoneNumber = Long.parseLong(lines[3].trim().split(regex)[1]);

            return new Profile(name, age, email, phoneNumber);
        } catch (FileNotFoundException e) {
            System.err.println("file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        File file = new File("src/main/resources/Profile.txt");
        Profile profile = fileReader.getDataFromFile(file);
        System.out.println(profile);
    }
}
