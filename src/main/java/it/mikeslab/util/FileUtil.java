package it.mikeslab.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUtil {


    public static void replaceFileContent(String filePath, String newContent) {

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.printf("File at %s does not exist.%n", filePath);
            return;
        }

        try (BufferedWriter out = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {

            out.write(newContent);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static List<String> findPathsRecursively(File directory, File initialDirectory) {

        System.out.println("Finding paths recursively in " + directory.getAbsolutePath());

        List<String> paths = new ArrayList<>();
        if (directory == null || !directory.exists()) {
            return paths;
        }

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                paths.addAll(findPathsRecursively(file, initialDirectory));
            } else {
                // Calculate the relative path
                String relativePath = initialDirectory.toURI().relativize(file.toURI()).getPath();
                paths.add(relativePath);
            }
        }
        return paths;
    }



}
