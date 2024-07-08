package it.mikeslab;

import it.mikeslab.util.Base64Util;
import it.mikeslab.util.FileUtil;
import it.mikeslab.util.HttpUtil;
import it.mikeslab.util.LicenseUtil;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LicenseUtil.inputLicenseKey();

        String targetDirectoryName = inputTargetDirectory();

        File directory = new File(targetDirectoryName);

        List<String> paths = FileUtil.findPathsRecursively(
                directory,
                directory
        );

        System.out.println("Found " + paths.size() + " files to replace.");

        String locale = inputLocale().toLowerCase();

        for (String path : paths) {

            if(!path.endsWith(".yml") && !path.endsWith(".yaml")) {
                System.out.println("Skipping " + path + " because it is not a YAML file.");
                continue;
            }

            String content = HttpUtil.getContent(locale, path, LicenseUtil.LICENSE_KEY);

            if(content == null) {
                continue;
            }

            String decodedContent = Base64Util.decode(content);

            path = targetDirectoryName + "/" + path;

            FileUtil.replaceFileContent(path, decodedContent);

        }

        System.out.printf("%d files have been replaced.", HttpUtil.REQUEST_COUNT);

    }

    public static String inputTargetDirectory() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the referring directory: ");

        return scanner.nextLine();
    }

    public static String inputLocale() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the locale: ");

        return scanner.nextLine();
    }

}